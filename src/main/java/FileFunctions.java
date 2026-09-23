import java.io.IOException;
import java.io.*;

public class FileFunctions {

    /**
     * Reads the first 10 lines of a file.
     *
     * @param user_input the path to the file
     * @throws IOException if the file cannot be read
     */
    public static void getHeader(String user_input) throws IOException {

        // BufferedReader can read the lines and FileReader opens the file.
        BufferedReader reader = new BufferedReader(new FileReader(user_input));

        String line = reader.readLine();
        int lineNumber = 0;

        // Take only 10 first lines.
        while (line != null && lineNumber < 10) {
            System.out.println(line);

            lineNumber++;
            line = reader.readLine();
        }

        reader.close();
    }
}

