package nl.bioinf.dsequencyzer;

import nl.bioinf.dsequencyzer.command.OutputCommand;
import nl.bioinf.dsequencyzer.io.ReadGTF;
import nl.bioinf.dsequencyzer.model.GTFParser;
import nl.bioinf.dsequencyzer.model.Gene;
import nl.bioinf.dsequencyzer.io.OutputHandler;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Provides functionality for processing, filtering and analysing GTF files.
 *
 * handles GTF file operations such as generating summaries or filtering features.
 */

public class GTFFunctions {

    // Log4j for exceptions
    private static final Logger logger =
            LogManager.getLogger(GTFFunctions.class);

    // Stores the original GTF headers
    private final List<String> headers;

    // Stores the original GTF lines
    private final List<String[]> lines;

    // Stores parsed genes and their connected transcripts and features
    private Map<String, Gene> genes;

    // Constructor
    public GTFFunctions(String inputFile)
            throws IOException {
        ReadGTF reader = new ReadGTF(inputFile);
        this.lines = reader.readFile();
        this.headers = reader.getHeaders();
    }

    /**
     * Parses the GTF file and returns all genes.
     * The parsed genes are stored so the file does not need to be parsed again.
     */
    public Map<String, Gene> parseGTF() {

        logger.info("Starting to parse GTF file.");

        if (genes == null) {
            GTFParser parser = new GTFParser(lines);
            genes = parser.parse();

            logger.info("GTF file parsed successfully.");
        }

        return genes;
    }

    /**
     * Returns the count of all features in the terminal.
     *
     * @throws IOException if the file does not end with .gtf (is not a .gtf file)
     */
    public Map<String, Integer> summaryCount()
            throws IOException {
        logger.info("Starting GTF summary");

        // Stores each feature and how often it occurs
        HashMap<String, Integer> features = new HashMap<>();

        // Stores each chromosome and gene
        HashMap<String, Integer> chromosomes = new HashMap<>();
        HashMap<String, Integer> genes = new HashMap<>();

        int total_lines = 0;

        logger.info("Starting to go through all lines.");

        // Reads input gtf-file
        for (String[] columns : lines) {
            total_lines++;

            String feature = columns[2];

            // Check if this feature has already been found
            if (features.containsKey(feature)) {
                // Add one to the existing count
                features.compute(feature, (k, count) -> count + 1);

            } else {
                // If feature not found -> start at 1
                features.put(feature, 1);
            }

            // Store each chromosome only once
            chromosomes.put(columns[0], 1);

            // Store each gene only once
            if (feature.equals("gene")) {
                genes.put(columns[8], 1);
            }
        }

        Map<String, Integer> summary = new HashMap<>();

        summary.put("total_lines", total_lines);
        summary.put("chromosomes", chromosomes.size());
        summary.put("genes", genes.size());

        logger.info("GTF summary data search completed.");

        return summary;
    }

    /**
     * Prints the count of all features in the terminal.
     */
    public void printSummary(Map<String, Integer> summary) {

        System.out.println("GTF Summary");
        System.out.println("Total lines: " + summary.get("total_lines"));
        System.out.println("Chromosomes: " + summary.get("chromosomes"));
        System.out.println("Genes: " + summary.get("genes"));

        logger.info("GTF summary printed successfully.");
    }


    /**
     * Filters on a gene and gives back all lines connected to the gene.
     * Saves those lines in a separate .gtf file.
     *
     * @param feature    input argument 2 - the feature the user wants to filter (exon, transcript, etc.)
     * @param chromosome input argument 3 - the chromosome the user wants to filter (chr1, chr2, etc.)
     * @param strand     input argument 4 - the strand the user wants to filter (+ / -)
     * @param outputMode using FILE, TERMINAL or BOTH, you can self bepeel how you want the output to show up.
     * @param outputFile name the output file or the standard name.
     * @throws IOException if the file does not end with .gtf (is not a .gtf file)
     */
    public void filterValues(
            String feature,
            String chromosome,
            String strand,
            OutputCommand.OutputMode outputMode,
            String outputFile)
            throws IOException {

        GTFFunctions.logger.info("Starting to search for values.");

        try (OutputHandler output = new OutputHandler(outputMode, outputFile)) {

            output.writeHeaders(headers);

            // Loop through all lines from the input file
            for (String[] columns : lines) {

                // Using a boolean for the different parameters
                boolean matches = true;

                // Check feature
                if (!feature.isEmpty() && !columns[2].equals(feature)) {
                    matches = false;
                }

                // Check chromosome
                if (!chromosome.isEmpty() && !columns[0].equals(chromosome)) {
                    matches = false;
                }

                // Check strand
                if (!strand.isEmpty() && !columns[6].equals(strand)) {
                    matches = false;
                }

                // Print and write lines if all filters match
                if (matches) {
                    String line = String.join("\t", columns);
                    output.write(line);
                }
            }
        }

        GTFFunctions.logger.info("Found all information that matched your values.");
    }


    /**
     * Filters a certain region between an end and start point on a chromosome.
     *
     * @param chromosome input a chromosome
     * @param start      input the start coordinates
     * @param end        input the end coordinates
     * @param outputMode using FILE, TERMINAL or BOTH, you can self bepeel how you want the output to show up.
     * @param outputFile name the output file or the standard name.
     * @throws IOException if the file does not end with .gtf (is not a .gtf file)
     *                     <p>
     *                     I am going to change this method into 3 or 4 different methods, because it performs too many tasks.
     *                     It will likely be split into one method for reading input, one for processing, and one for writing the output.
     *
     *                     NOTITIE: Dit staat nu ook in de Parser, hier weg?
     */
    public void findRegion(
            String chromosome,
            String start,
            String end,
            String outputFile,
            OutputCommand.OutputMode outputMode)
            throws IOException {

        // Makes the values integers
        int region_start = Integer.parseInt(start);
        int region_end = Integer.parseInt(end);

        try (OutputHandler output = new OutputHandler(outputMode, outputFile)) {

            output.writeHeaders(headers);

            logger.info("Searching in your region.");

            // Loop through all lines from the input file
            for (String[] columns : lines) {

                // Makes the values integers
                int feature_start = Integer.parseInt(columns[3]);
                int feature_end = Integer.parseInt(columns[4]);

                // Check chromosome and region
                if (columns[0].equals(chromosome)
                        && feature_start <= region_start
                        && feature_end >= region_end) {

                    String line = String.join("\t", columns);
                    output.write(line);
                }
            }
        }
        logger.info("Collected all data in your region successfully.");
    }


    /**
     * Counts how often each chromosome occurs in the GTF file.
     * Prints an overview of the chromosomes and their counts.
     *
     * @throws IOException if an error occurs while reading the GTF file
     */
    public Map<String, Integer> chromosomeCount()
            throws IOException {

        // Stores each chromosome and how often it occurs
        HashMap<String, Integer> chromosomes = new HashMap<>();

        logger.info("Starting to count all the chromosomes.");

        // Loop through all lines from the input file
        for (String[] columns : lines) {

            String chromosome = columns[0];

            // Check if this chromosome has already been found
            if (chromosomes.containsKey(chromosome)) {

                // Add one to the existing count
                int count = chromosomes.get(chromosome);
                chromosomes.put(chromosome, count + 1);

            } else {

                // If chromosome not found, start at 1
                chromosomes.put(chromosome, 1);
            }
        }

        // Print chromosome overview
        System.out.println("Chromosome overview");

        // Print each key and value
        for (String chromosome : chromosomes.keySet()) {
            System.out.println(chromosome + " : " + chromosomes.get(chromosome));
        }

        logger.info("Counted all chromosomes in GTF file.");

        return chromosomes;
    }

    /**
     * Searches for the input gene based on the gene_id.
     * The lines associated with the gene can be added in a generated output file.
     *
     * @param geneId     the name of a gene you want to search for.
     * @param outputMode using FILE, TERMINAL or BOTH, you can self bepeel how you want the output to show up.
     * @param outputFile name the output file or the standard name.
     */
    public void findGene(
            String geneId,
            OutputCommand.OutputMode outputMode,
            String outputFile)
            throws IOException {

        // Parse the GTF file
        Map<String, Gene> genes = parseGTF();

        logger.info("Starting to try and find your gene.");

        // Find the requested gene
        Gene gene = genes.get(geneId);

        if (gene != null) {

            // Write all lines connected to the gene
            try (OutputHandler output = new OutputHandler(outputMode, outputFile)) {

                output.writeHeaders(headers);

                // Loop through all original GTF lines
                for (String[] columns : lines) {

                    // Check if the gene_id matches
                    if (columns[8].contains("gene_id \"" + geneId + "\"")) {

                        String line = String.join("\t", columns);
                        output.write(line);
                    }
                }
            }
            logger.info("Found your gene and matches.");

        } else {
            System.out.println("Gene not found.");
        }
    }
}

