import java.io.*;

public class GTFFunctions {

    /**
     * Returns the specific column or data requested by the user in a GTF-file.
     *
     * @param input_file the path to the file
     * @throws IOException if the file does not end with .gtf (is not a .gtf file)
     */
    public static int countFeatures(String input_file, String feature) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(input_file));

        String line = reader.readLine();
        int count = 0;

        while (line != null) {

            if (!line.startsWith("#")) {
                String[] columns = line.split("\t");

                if (columns[2].equals(feature)) {
                    count++;
                }
            }

            line = reader.readLine();
        }

        reader.close();

        return count;
    }
}

    public static int