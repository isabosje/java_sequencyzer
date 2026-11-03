package nl.bioinf.dsequencyzer.command;

import nl.bioinf.dsequencyzer.GTFFunctions;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

import java.io.IOException;
import java.util.Map;

@Command(
        name = "summary",
        // The @|bold is used to make --help more plain
        description = "@|bold Gives a summary containing features depending on file type.|@"
)
public class SummaryCommand implements Runnable {

    // Inserts InputCommand
    @Mixin
    InputCommand input = new InputCommand();

    @Mixin
    VerboseCommand verbose = new VerboseCommand();

    @Override
    public void run() {
        verbose.run();

        // Catch exception while reading file
        try {
            GTFFunctions gtf = new GTFFunctions(input.inputFile);

            Map<String, Integer> summary = gtf.summaryCount();

            gtf.printSummary(summary);

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}



