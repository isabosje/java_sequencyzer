package nl.bioinf.dsequencyzer.command;

import nl.bioinf.dsequencyzer.GTFFunctions;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

import java.io.IOException;

/**
 * Subcommand that provides an overview of the annotations
 * found per chromosome in a GTF file.
 */
@Command(
        name = "chromosome",
        // The @|bold is used to make --help more plain
        description = "@|bold Gives an overview of annotations per chromosome.|@"
)
public class ChromosomeCommand implements Runnable {

    // Inserts InputCommand
    @Mixin
    InputCommand input = new InputCommand();

    @Mixin
    VerboseCommand verbose = new VerboseCommand();

    @Override
    public void run() {
        verbose.run();
        /**
         * Executes the chromosome subcommand.
         *
         * If an IOException occurs while reading the input file,
         * and error message is printed.
         */
        try {
            GTFFunctions gtf = new GTFFunctions(input.inputFile);
            gtf.chromosomeCount();

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}
