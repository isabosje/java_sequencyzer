import java.io.*;
import java.io.IOException;
import java.util.HashMap;

public class GTFFunctions {

    /**
     * Returns the count of all features in the terminal.
     *
     * @param input_file the path to the file
     * @throws IOException if the file does not end with .gtf (is not a .gtf file)
     */
    public static void summaryCount(String input_file) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(input_file));

        String line = reader.readLine();

        // Stores each feature and how often it occurs
        // I used the next site: https://www.w3schools.com/java/java_hashmap.asp#:~:text=A%20HashMap%20stores%20items%20in%20key%2Fvalue%20pairs%2C%20where,use%20a%20key%20to%20retrieve%20its%20associated%20value.
        HashMap<String, Integer> features = new HashMap<>();

        while (line != null) {

            // Skip comments and header lines
            if (!line.startsWith("#")) {

                String[] columns = line.split("\t");
                String feature = columns[2];

                // Check if this feature has already been found
                if (features.containsKey(feature)) {

                    // Add one to the existing count
                    int count = features.get(feature);
                    features.put(feature, count + 1);

                } else {

                    // If feature not found-> start at 1
                    features.put(feature, 1);
                }
            }

            line = reader.readLine();
        }

        reader.close();

        // Print summary + features and count together
        System.out.println("GTF Summary");
        for (String feature : features.keySet()) {
            System.out.println(feature + " : " + features.get(feature));
        }
    }

    /**
     * Filters on a gene and gives back all lines connected to the gene.
     * Saves those lines in a seperate .gtf file.
     *
     * @param input_file the path to the file
     * @param option     input argument 2
     * @throws IOException if the file does not end with .gtf (is not a .gtf file)
     */
    public static void findValue(String input_file, String option, String value)
            throws IOException {

        // Read .gtf file
        BufferedReader reader = new BufferedReader(new FileReader(input_file));

        // Writes new .gtf file with chosen lines
        BufferedWriter writer = new BufferedWriter(new FileWriter(value + ".gtf"));

        String line = reader.readLine();

        while (line != null) {

            if (!line.startsWith("#")) {

                String[] columns = line.split("\t");

                if (option.equals("gene")) {

                    if (columns[8].contains(value)) {
                        System.out.println(line);
                        writer.write(line);
                        writer.newLine();
                    }

                } else if (option.equals("chromosome")) {

                    if (columns[0].equals(value)) {
                        System.out.println(line);
                        writer.write(line);
                        writer.newLine();
                    }

                } else if (option.equals("feature")) {

                    if (columns[2].equals(value)) {
                        System.out.println(line);
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }

            // Read next line
            line = reader.readLine();
        }

        reader.close();
        writer.close();
    }

    /**
     * Filters a certain region between an end and start point on a chromosome.
     *
     * @param input_file the path to the file
     * @param chromosome input a chromosome
     * @param start input the start coordinates
     * @param end input the end coordinates
     * @throws IOException if the file does not end with .gtf (is not a .gtf file)
     */
    public static void findRegion(String input_file, String chromosome, String start, String end) throws IOException {
        // Read .gtf file
        BufferedReader reader = new BufferedReader(new FileReader(input_file));

        // Writes new .gtf file with chosen lines
        BufferedWriter writer = new BufferedWriter(new FileWriter(chromosome + ".gtf"));

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
}


