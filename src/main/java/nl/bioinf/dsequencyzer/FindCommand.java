package nl.bioinf.dsequencyzer;

import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(
        name = "FindCommand",
        description = "Finds features")

public class FindCommand implements Runnable {

    @CommandLine.Option(names = {"-i", "--input"},
            description = "Input GTF file")
    String input_file;

    @CommandLine.Option(names = {"-o", "--option"},
            description = "What to search for: gene, chromosome or feature")
    String option;

    @CommandLine.Option(names = {"-v", "--value"},
            description = "Value to search for")
    String value;

    @Override
    public void run() {

        try {
            GTFFunctions gtf = new GTFFunctions(input_file);
            gtf.findValue(option, value);

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}

