package nl.bioinf.dsequencyzer;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.IOException;

@Command(
        name = "--find",
        description = "Searches for given features in a GTF file with a value input option.")

public class FindCommand implements Runnable {

    @Option(
            names = {"-i, --input"},
            description = "Input a GTF file",
            required = true)
    String input_file;

    @Option(names = {"-o", "--option"},
            description = "What to search for: gene, chromosome or feature")
    String option;

    @Option(names = {"-v", "--value"},
            description = "Value to search for" +
    "Can be a gene name, chromosome number or feature type for instance.")
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

