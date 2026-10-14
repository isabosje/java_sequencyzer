package nl.bioinf.dsequencyzer.command;

import nl.bioinf.dsequencyzer.GTFFunctions;
import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(
        name = "chromosome",
        description = "@|bold Gives an overview of annotations per chromosome.|@"
)

public class ChromosomeCommand implements Runnable {

    @CommandLine.Mixin
    InputCommand input = new InputCommand();

    @Override
    public void run() {

        try {
            GTFFunctions gtf = new GTFFunctions(input.input_file);
            gtf.chromosomeCount();

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}
