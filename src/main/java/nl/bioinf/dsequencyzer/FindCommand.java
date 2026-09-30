package nl.bioinf.dsequencyzer;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.ArgGroup;
import java.io.IOException;

@Command(
        name = "--find",
        description = "@|bold Searches for given features in a GTF file with a value input option.|@")

public class FindCommand implements Runnable {

    @ArgGroup(
            validate = false,
            heading = "%n@|bold,cyan Input:|@%n",
            order = 1
    )
    InputGroup Input;

    @ArgGroup(
            validate = false,
            heading = "%n@|bold,cyan Search:|@%n",
            order = 2
    )
    SearchGroup Search;

    static class InputGroup {

        @Option(
                names = {"-i, --input"},
                description = "Input a GTF file",
                required = true)
        String input_file;

    }

    static class SearchGroup {

    @Option(names = {"-o", "--option"},
            description = "What to search for: gene, chromosome or feature")
    String option;

    @Option(names = {"-v", "--value"},
            description = "Value to search for" +
    "Can be a gene name, chromosome number or feature type for instance.")
    String value;

    }

    @Override
    public void run() {

        try {
            GTFFunctions gtf = new GTFFunctions(Input.input_file);
            gtf.findValue(Search.option, Search.value);

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}

