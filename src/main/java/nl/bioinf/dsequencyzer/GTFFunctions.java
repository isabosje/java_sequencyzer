package nl.bioinf.dsequencyzer;

import nl.bioinf.dsequencyzer.io.ReadGTF;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GTFFunctions {

    // Constructor
    private List<String[]> lines;

    public GTFFunctions(String inputFile) throws IOException {
        ReadGTF reader = new ReadGTF(inputFile);
        this.lines = reader.readFile();
    }

    // Log4j for exceptions
    private static final Logger logger =
            LogManager.getLogger(GTFFunctions.class);

    /**
     * Returns the count of all features in the terminal.
     *
     * @throws IOException if the file does not end with .gtf (is not a .gtf file)
     */
    public void summaryCount() throws IOException {
        logger.info("Starting GTF summary");

        // Stores each feature and how often it occurs
        HashMap<String, Integer> features = new HashMap<>();

        // Stores each chromosome and gene
        HashMap<String, Integer> chromosomes = new HashMap<>();
        HashMap<String, Integer> genes = new HashMap<>();

        int total_lines = 0;

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

            // Prints summary, features and count
            System.out.println("GTF Summary");
            System.out.println("Total annotation lines: " + total_lines);
            System.out.println("Unique chromosomes: " + chromosomes.size());
            System.out.println("Genes: " + genes.size());

            // Prints keys with values
            for (String featureName : features.keySet()) {
                System.out.println(featureName + " : " + features.get(featureName));
            }

            logger.info("GTF summary completed. Total lines: {}", total_lines);
        }
    }


    /**
     * Filters on a gene and gives back all lines connected to the gene.
     * Saves those lines in a separate .gtf file.
     *
     * @param feature    input argument 2 - the feature the user wants to filter (exon, transcript, etc.)
     * @param chromosome input argument 3 - the chromosome the user wants to filter (chr1, chr2, etc.)
     * @param strand     input argument 4 - the strand the user wants to filter (+ / -)
     * @throws IOException if the file does not end with .gtf (is not a .gtf file)
     */
    public void findValues(String feature, String chromosome, String strand,
                           String output_file) throws IOException {

        GTFFunctions.logger.info("Starting to search for values");

        BufferedWriter writer = null;

        // Create output file only if the user specified one
        // TODO: need to make an output_file option / subcommand / class
        if (!output_file.isEmpty()) {
            writer = new BufferedWriter(new FileWriter(output_file + ".gtf"));
        }

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

                // Print matching lines in the terminal
                System.out.println(line);

                // Write matching lines to the output file if specified
                if (writer != null) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }

        // Close output file if one was created
        // TODO: still needs the code to work
        if (writer != null) {
            writer.close();
        }
        GTFFunctions.logger.info("Found all information that matched your values.");
    }


    /**
     * Filters a certain region between an end and start point on a chromosome.
     *
     * @param chromosome input a chromosome
     * @param start      input the start coordinates
     * @param end        input the end coordinates
     * @throws IOException if the file does not end with .gtf (is not a .gtf file)
     *                     <p>
     *                     I am going to change this method into 3 or 4 different methods, because it performs too many tasks.
     *                     It will likely be split into one method for reading input, one for processing, and one for writing the output.
     *
     *                     NOTITIE: Dit staat nu ook in de Parser, hier weg?
     */
    public void findRegion(String chromosome, String start, String end, String output_file)
            throws IOException {

        BufferedWriter writer = null;

        // Create output file only if the user specified one
        if (!output_file.isEmpty()) {
            writer = new BufferedWriter(new FileWriter(output_file + ".gtf"));
        }

        // Makes the values integers
        int region_start = Integer.parseInt(start);
        int region_end = Integer.parseInt(end);

        // Loop through all lines from the input file
        for (String[] columns : lines) {

            // Makes the values integers
            int feature_start = Integer.parseInt(columns[3]);
            int feature_end = Integer.parseInt(columns[4]);

            // Check chromosome and region
            if (columns[0].equals(chromosome)
                    && feature_start >= region_start
                    && feature_end <= region_end) {

                String line = String.join("\t", columns);

                // Print matching lines in the terminal
                System.out.println(line);

                // Write matching lines to the output file if specified
                if (writer != null) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }

        // Close output file if one was created
        // TODO: same as findValue
        if (writer != null) {
            writer.close();
        }
    }


    /**
     * Counts how often each chromosome occurs in the GTF file.
     * Prints an overview of the chromosomes and their counts.
     *
     * @throws IOException if an error occurs while reading the GTF file
     */
    public void chromosomeCount() throws IOException {

        // Stores each chromosome and how often it occurs
        HashMap<String, Integer> chromosomes = new HashMap<>();

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
    }
}

