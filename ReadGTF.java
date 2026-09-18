import java.io.*;

public class ReadGTF {

    public static String getGTFPath(String filePath, String type) throws IOException {
        if (filePath.endsWith(".gtf")) {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line = reader.readLine();

            while (line != null) {

                if (!line.startsWith("#")) {
                    String[] columns = line.split("\t");

                    if (type.equals("seqname")) {
                        return columns[0];
                    }

                    if (type.equals("source")) {
                        return columns[1];
                    }

                    if (type.equals("feature")) {
                        return columns[2];
                    }

                    if (type.equals("start")) {
                        return columns[3];
                    }

                    if (type.equals("end")) {
                        return columns[4];
                    }

                    if (type.equals("score")) {
                        return columns[5];
                    }

                    if (type.equals("strand")) {
                        return columns[6];
                    }

                    if (type.equals("frame")) {
                        return columns[7];
                    }

                    if (type.equals("attributes")) {
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
