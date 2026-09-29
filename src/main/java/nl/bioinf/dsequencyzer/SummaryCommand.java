package nl.bioinf.dsequencyzer;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.IOException;

@Command(
        name = "--summary",
        description = "Gives a summary containing features depending on file type.")

public class SummaryCommand implements Runnable {
    @Option(
            names = {"-i, --input"},
            description = "Input a valid GTF or FASTA file",
            required = true)
    String input_file;

    @Override
    public void run() {

        // Needs changes for different filetypes
        try {
            GTFFunctions gtf = new GTFFunctions(input_file);
            gtf.summaryCount();

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}


