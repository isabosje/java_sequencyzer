package nl.bioinf.dsequencyzer.command;

import nl.bioinf.dsequencyzer.GTFFunctions;
import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(
        name = "summary",
        // The @|bold is used to make --help more plain
        description = "@|bold Gives a summary containing features depending on file type.|@"
)
public class SummaryCommand implements Runnable {

    // Inserts InputCommand
    @CommandLine.Mixin
    InputCommand input = new InputCommand();

    @Override
    public void run() {
        // Catch exception while reading file
        try {
            GTFFunctions gtf = new GTFFunctions(input.inputFile);
            gtf.summaryCount();

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}



