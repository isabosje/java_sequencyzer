package nl.bioinf.dsequencyzer.command;

import nl.bioinf.dsequencyzer.GTFFunctions;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.ArgGroup;
import java.io.IOException;

@Command(
        name = "find",
        description = "@|bold Searches for given features in a GTF file with a value input %n" +
                      "option.|@")

public class FindCommand implements Runnable {

    @ArgGroup(
            validate = false,
            heading = "%n@|bold,blue Input:|@%n",
            order = 1
    )
    InputGroup Input;

    @ArgGroup(
            validate = false,
            heading = "%n@|bold,blue Search:|@%n",
            order = 2
    )
    SearchGroup Search;

    static class InputGroup {

        @Option(
                names = {"-i, --input"},
                description = "@|bold Input a valid GTF file.|@",
                required = true)
        String input_file;

    }

    static class SearchGroup {

    @Option(names = {"-o", "--option"},
            description = "@|bold What to search for: gene, chromosome or feature.|@")
    String option;

    @Option(names = {"-v", "--value"},
            description = "@|bold Value to search for. |@%n" +
                          "@|bold Can be a gene name, chromosome number or feature type.|@")
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

