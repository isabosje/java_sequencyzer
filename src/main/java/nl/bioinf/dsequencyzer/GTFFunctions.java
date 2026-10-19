package nl.bioinf.dsequencyzer;

import java.io.*;
import java.util.HashMap;

public class GTFFunctions {

    private String input_file;

    // Constructor
    public GTFFunctions(String input_file) {
        this.input_file = input_file;
    }

    /**
     * Returns the count of all features in the terminal.
     *
     * @throws IOException if the file does not end with .gtf (is not a .gtf file)
     */
    public void summaryCount() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(input_file));

        String line = reader.readLine();

        // Stores each feature and how often it occurs
        // I used the next site: https://www.w3schools.com/java/java_hashmap.asp
        HashMap<String, Integer> features = new HashMap<>();

        // Stores each chromosome and gene
        HashMap<String, Integer> chromosomes = new HashMap<>();
        HashMap<String, Integer> genes = new HashMap<>();

        // Counts all annotation lines
        int total_lines = 0;

        while (line != null) {

            // Skip comments and header lines
            if (!line.startsWith("#")) {

                String[] columns = line.split("\t");

                // Count total annotation lines
                total_lines++;

                String feature = columns[2];

                // Check if this feature has already been found
                if (features.containsKey(feature)) {

                    // Add one to the existing count
                    int count = features.get(feature);
                    features.put(feature, count + 1);

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

            line = reader.readLine();
        }

        reader.close();

        // Print summary + features and count together
        System.out.println("GTF Summary");
        System.out.println("Total annotation lines: " + total_lines);
        System.out.println("Unique chromosomes: " + chromosomes.size());
        System.out.println("Genes: " + genes.size());

        for (String feature : features.keySet()) {
            System.out.println(feature + " : " + features.get(feature));
        }
    }

    /**
     * Filters on a gene and gives back all lines connected to the gene.
     * Saves those lines in a separate .gtf file.
     *
     * @param feature       input argument 2 - the feature the user wants to filter (exon, transcript, etc.)
     * @param chromosome    input argument 3 - the chromosome the user wants to filter (chr1, chr2, etc.)
     * @param strand        input argument 4 - the strand the user wants to filter (+ / -)
     * @throws IOException  if the file does not end with .gtf (is not a .gtf file)
     */
    public void findValues(String feature, String chromosome, String strand, String output_file)
            throws IOException {

        // Read .gtf file
        BufferedReader reader = new BufferedReader(new FileReader(input_file));

        // Writes new .gtf file with chosen lines
        BufferedWriter writer = new BufferedWriter(new FileWriter(output_file + ".gtf"));

        String line = reader.readLine();

        while (line != null) {

            if (!line.startsWith("#")) {

                String[] columns = line.split("\t");

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
                    System.out.println(line);
                    writer.write(line);
                    writer.newLine();
                }
            }

            line = reader.readLine();
        }

        reader.close();
        writer.close();
    }

    /**
     * Filters a certain region between an end and start point on a chromosome.
     *
     * @param chromosome   input a chromosome
     * @param start        input the start coordinates
     * @param end          input the end coordinates
     * @throws IOException if the file does not end with .gtf (is not a .gtf file)
     */
    public void findRegion(String chromosome, String start, String end, String output_file) throws IOException {
        // Op gaan splitsen in verschillende methoden (inlezen + filteren + uitschrijven).

        // Read .gtf file
        BufferedReader reader = new BufferedReader(new FileReader(input_file));

        // Writes new .gtf file with chosen lines
        BufferedWriter writer = new BufferedWriter(new FileWriter(output_file + ".gtf"));

        // Needs to be integer because of numbers
        int region_start = Integer.parseInt(start);
        int region_end = Integer.parseInt(end);

        String line = reader.readLine();

        while (line != null) {

            if (!line.startsWith("#")) {

                String[] columns = line.split("\t");

                // Also needs to be integers because numbers
                int feature_start = Integer.parseInt(columns[3]);
                int feature_end = Integer.parseInt(columns[4]);

                // First arg is chromosome number
                if (columns[0].equals(chromosome)) {

                    // Region falls in between the start and end in the gtf file
                    if (feature_start >= region_start && feature_end <= region_end) {

                        System.out.println(line);

                        writer.write(line);
                        writer.newLine();
                    }
                }
            }

            line = reader.readLine();
        }

        reader.close();
        writer.close();
    }

    public void chromosomeCount() throws IOException {

        // Read GTF file
        BufferedReader reader = new BufferedReader(new FileReader(input_file));

        String line = reader.readLine();

        // Stores each chromosome and how often it occurs
        HashMap<String, Integer> chromosomes = new HashMap<>();

        while (line != null) {

            // Skip comments and header lines
            if (!line.startsWith("#")) {

                String[] columns = line.split("\t");
                String chromosome = columns[0];

                // Check if this chromosome has already been found
                if (chromosomes.containsKey(chromosome)) {

                    // Add one to the existing count
                    int count = chromosomes.get(chromosome);
                    chromosomes.put(chromosome, count + 1);

                } else {

                    // If chromosome not found -> start at 1
                    chromosomes.put(chromosome, 1);
                }
            }

            // Read next line
            line = reader.readLine();
        }

        reader.close();

        // Print chromosome overview
        System.out.println("Chromosome overview");

        for (String chromosome : chromosomes.keySet()) {
            System.out.println(chromosome + " : " + chromosomes.get(chromosome));
        }
    }
}

