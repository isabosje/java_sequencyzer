package nl.bioinf.dsequencyzer;

import java.io.IOException;

public class SummaryCommand {

    public void run(String input_file) {

        try {
            GTFFunctions gtf = new GTFFunctions(input_file);
            gtf.summaryCount();

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}


