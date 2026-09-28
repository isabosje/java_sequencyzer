package nl.bioinf.dsequencyzer;
import java.io.IOException;

public class SummaryCommand {

    public static void run(String input_file) {
        try {
            GTFFunctions.summaryCount(input_file);
        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}
