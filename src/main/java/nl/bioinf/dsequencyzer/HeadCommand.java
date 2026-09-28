package nl.bioinf.dsequencyzer;
import java.io.IOException;

public class HeadCommand {

    public static void run(String input_file) {

        try {
            FileFunctions.getHeader(input_file);
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }
}
