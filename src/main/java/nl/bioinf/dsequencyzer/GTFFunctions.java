package nl.bioinf.dsequencyzer;

import nl.bioinf.dsequencyzer.io.ReadGTF;
import nl.bioinf.dsequencyzer.model.GTFParser;
import nl.bioinf.dsequencyzer.model.Gene;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides functions to process and filter a GTF file.
 * The file is read and parsed once when a GTFFunctions object is created.
 */
public class GTFFunctions {

    private final List<String[]> gtfLines;
    private final Map<String, Gene> geneMap;

    /**
     * Reads the GTF file and parses its contents into genes.
     *
     * @param inputFile the path to the input GTF file
     * @throws IOException if the file cannot be read or is not a valid GTF file
     */
    public GTFFunctions(String inputFile) throws IOException {

        // Read the GTF file
        ReadGTF readGTF = new ReadGTF(inputFile);
        this.gtfLines = readGTF.readFile();

        // Parse the GTF lines
        GTFParser parser = new GTFParser(gtfLines);
        this.geneMap = parser.parse();
    }

    /**
     * Counts the annotation lines, unique chromosomes, genes and feature types.
     * Prints the summary in the terminal.
     */
    public void summaryCount() {

        // Create hashmaps
        Map<String, Integer> features = new HashMap<>();
        Map<String, Integer> chromosomes = new HashMap<>();

        int total_lines = 0;

        // For each line in columns
        for (String[] columns : gtfLines) {

            total_lines++;

            String feature = columns[2];

            // Count features
            if (features.containsKey(feature)) {
                int count = features.get(feature);
                features.put(feature, count + 1);
            } else {
                features.put(feature, 1);
            }

            // Store each chromosome
            chromosomes.put(columns[0], 1);
        }

        // Print summary
        System.out.println("GTF Summary");
        System.out.println("Total annotation lines: " + total_lines);
        System.out.println("Unique chromosomes: " + chromosomes.size());
        System.out.println("Genes: " + geneMap.size());

        for (String feature : features.keySet()) {
            System.out.println(feature + " : " + features.get(feature));
        }
    }

    /**
     * Filters the GTF file by feature, chromosome and strand.
     * Saves all matching lines in a separate GTF file and prints a maximum
     * of three matching lines in the terminal.
     *
     * @param feature the feature to filter, for example exon or transcript
     * @param chromosome the chromosome to filter, for example chr1 or chr2
     * @param strand the strand to filter (+ or -)
     * @param output_file the name of the output file, without the .gtf extension
     * @throws IOException if the output file cannot be written
     */
    public void findValues(String feature, String chromosome, String strand,
                           String output_file) throws IOException {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(output_file + ".gtf"))) {

            int printed_lines = 0;

            for (String[] columns : gtfLines) {

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

                    // Print a maximum of 3 lines in the terminal
                    if (printed_lines < 3) {
                        System.out.println(line);
                        printed_lines++;
                    }

                    // Write all matching lines to the output file
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
    }

    /**
     * Filters features on a chromosome that fall completely within a region.
     * Saves all matching lines in a separate GTF file and prints a maximum
     * of three matching lines in the terminal.
     *
     * @param chromosome the chromosome to search
     * @param start the start coordinate of the region
     * @param end the end coordinate of the region
     * @param output_file the name of the output file, without the .gtf extension
     * @throws IOException if the output file cannot be written
     * @throws NumberFormatException if start or end is not a valid integer
     */
    public void findRegion(String chromosome, String start, String end,
                           String output_file) throws IOException {

        // Convert the coordinates to integers
        int region_start = Integer.parseInt(start);
        int region_end = Integer.parseInt(end);

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(output_file + ".gtf"))) {

            int printed_lines = 0;

            for (String[] columns : gtfLines) {

                int feature_start = Integer.parseInt(columns[3]);
                int feature_end = Integer.parseInt(columns[4]);

                // Check chromosome
                if (columns[0].equals(chromosome)) {

                    // Check if the feature falls completely within the region
                    if (feature_start >= region_start && feature_end <= region_end) {

                        String line = String.join("\t", columns);

                        // Print a maximum of 3 lines in the terminal
                        if (printed_lines < 3) {
                            System.out.println(line);
                            printed_lines++;
                        }

                        // Write matching lines to the output file
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }
        }
    }

    /**
     * Counts how often each chromosome occurs in the GTF file.
     * Prints the chromosome overview in the terminal.
     */
    public void chromosomeCount() {

        // Stores each chromosome and how often it occurs
        Map<String, Integer> chromosomes = new HashMap<>();

        for (String[] columns : gtfLines) {

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

        // Print each chromosome and its count
        for (String chromosome : chromosomes.keySet()) {
            System.out.println(chromosome + " : " + chromosomes.get(chromosome));
        }
    }

    /**
     * Filters a gene and retrieves all GTF lines connected to that gene.
     * Saves the matching lines in a separate GTF file.
     *
     * @param geneId the ID of the gene to find
     * @param output_file the name of the output file, without the .gtf extension
     * @throws IOException if the output file cannot be written
     */
    public void findGene(String geneId, String output_file) throws IOException {

        // Create boolean found
        boolean found = false;

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(output_file + ".gtf"))) {

            for (String[] columns : gtfLines) {

                // Contains all IDs
                String attributes = columns[8];

                // Check whether the line belongs to the requested gene
                if (attributes.contains("gene_id \"" + geneId + "\"")) {

                    String line = String.join("\t", columns);

                    writer.write(line);
                    writer.newLine();

                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No entries found for gene: " + geneId);
        }
    }

    /**
     * Returns the parsed genes from the GTF file.
     *
     * @return a map containing gene IDs and their corresponding Gene objects
     */
    public Map<String, Gene> getGeneMap() {
        return geneMap;
    }
}