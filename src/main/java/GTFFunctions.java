import java.io.*;

public class GTFFunctions {

    /**
     * Returns the specific column or data requested by the user in a GTF-file.
     *
     * @param user_input the path to the file
     * @throws IOException if the file does not end with .gtf (is not a .gtf file)
     */
    public static String getGTFColumn(String user_input, String gtf_option) throws IOException {
        if (user_input.endsWith(".gtf")) {

            // BufferedReader can read the lines and FileReader opens the file.
            BufferedReader reader = new BufferedReader(new FileReader(user_input));

            String line = reader.readLine();

            while (line != null) {

                // Skip all the # in the beginning (= no data).
                if (!line.startsWith("#")) {
                    String[] columns = line.split("\t");

                    if (gtf_option.equals("seqname")) {
                        return columns[0];
                    }

                    if (gtf_option.equals("source")) {
                        return columns[1];
                    }

                    if (gtf_option.equals("feature")) {
                        return columns[2];
                    }

                    if (gtf_option.equals("start")) {
                        return columns[3];
                    }

                    if (gtf_option.equals("end")) {
                        return columns[4];
                    }

                    if (gtf_option.equals("score")) {
                        return columns[5];
                    }

                    if (gtf_option.equals("strand")) {
                        return columns[6];
                    }

                    if (gtf_option.equals("frame")) {
                        return columns[7];
                    }

                    if (gtf_option.equals("attributes")) {
                        return columns[8];
                    }
                }

                line = reader.readLine();
            }

            reader.close();
        }

        return "";
    }
}
