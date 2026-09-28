package nl.bioinf.dsequencyzer;
import java.io.IOException;
import java.io.*;

public class FileFunctions {

    private String input_file;

    public FileFunctions(String input_file) {
        this.input_file = input_file;
    }

    /**
     * Returns the first 10 lines of a file in the terminal.
     *
     * @throws IOException if the file cannot be read
     */
    public void getHeader() throws IOException {

        // Read input file
        BufferedReader reader = new BufferedReader(new FileReader(input_file));

        String line = reader.readLine();
        int lineNumber = 0;

        // Print the first 10 lines
        while (line != null && lineNumber < 10) {

            System.out.println(line);

            lineNumber++;
            line = reader.readLine();
        }

        reader.close();
    }
}

