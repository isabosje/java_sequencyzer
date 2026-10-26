package nl.bioinf.dsequencyzer.command;

import nl.bioinf.dsequencyzer.GTFFunctions;
import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(
        name = "chromosome",
        // The @|bold is used to make --help more plain
        description = "@|bold Gives an overview of annotations per chromosome.|@"
)
public class ChromosomeCommand implements Runnable {

    // Inserts InputCommand
    @CommandLine.Mixin
    InputCommand input = new InputCommand();

    @Override
    public void run() {
        // Catch exception while reading file
        try {
            GTFFunctions gtf = new GTFFunctions(input.inputFile);
            gtf.chromosomeCount();

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}
