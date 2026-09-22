import java.io.*;

public class ReadGTF {

    public static String getGTFPath(String filePath, String option) throws IOException {
        if (filePath.endsWith(".gtf")) {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line = reader.readLine();

            while (line != null) {

                if (!line.startsWith("#")) {
                    String[] columns = line.split("\t");

                    if (option.equals("seqname")) {
                        return columns[0];
                    }

                    if (option.equals("source")) {
                        return columns[1];
                    }

                    if (option.equals("feature")) {
                        return columns[2];
                    }

                    if (option.equals("start")) {
                        return columns[3];
                    }

                    if (option.equals("end")) {
                        return columns[4];
                    }

                    if (option.equals("score")) {
                        return columns[5];
                    }

                    if (option.equals("strand")) {
                        return columns[6];
                    }

                    if (option.equals("frame")) {
                        return columns[7];
                    }

                    if (option.equals("attributes")) {
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
