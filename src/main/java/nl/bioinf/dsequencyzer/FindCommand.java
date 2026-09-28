package nl.bioinf.dsequencyzer;
import java.io.IOException;

public class FindCommand {
    public static void run(String input_file, String option, String value) {

        try {
            GTFFunctions.findValue(input_file, option, value);
        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}
