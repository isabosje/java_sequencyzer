package nl.bioinf.dsequencyzer.command;

import nl.bioinf.dsequencyzer.GTFFunctions;
import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(
        name = "summary",
        description = "@|bold Gives a summary containing features depending on file type.|@"
)
public class SummaryCommand implements Runnable {

    @CommandLine.Mixin
    InputCommand input = new InputCommand();

    @Override
    public void run() {

        try {
            GTFFunctions gtf = new GTFFunctions(input.input_file);
            gtf.summaryCount();

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}



