package nl.bioinf.dsequencyzer.command;

import nl.bioinf.dsequencyzer.GTFFunctions;
import picocli.CommandLine;
import java.io.IOException;

@CommandLine.Command(
        name = "SummaryCommand",
        description = "Gives summary gtf file")

public class SummaryCommand implements Runnable {
    @CommandLine.Option(names = {"-i", "--input"},
            description = "Input GTF file")
    String input_file;

    @Override
    public void run() {

        try {
            GTFFunctions gtf = new GTFFunctions(input_file);
            gtf.summaryCount();

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}


