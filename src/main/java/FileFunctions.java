import java.io.IOException;

public class FileFunctions {

    /**
     * Reads the first 10 lines of a file.
     *
     * @param filePath the path to the file
     * @throws IOException if the file cannot be read
     */
    public static void getHeader(String filePath) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line = reader.readLine();
        int lineNumber = 0;

        while (line != null && lineNumber < 10) {
            System.out.println(line);

            lineNumber++;
            line = reader.readLine();
        }

        reader.close();
    }
}

