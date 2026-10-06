package nl.bioinf.dsequencyzer.command;

import nl.bioinf.dsequencyzer.GTFFunctions;
import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(
        name = "find",
        description = "@|bold Searches for given features in a GTF file with a value input %n" +
                "option.|@"
)
public class FindCommand implements Runnable {

    @CommandLine.ArgGroup(
            validate = false,
            heading = "%n@|bold,blue Input:|@%n",
            order = 1
    )
    InputCommand input = new InputCommand();

    @CommandLine.ArgGroup(
            validate = false,
            heading = "%n@|bold,blue Search:|@%n",
            order = 2
    )
    SearchGroup Search = new SearchGroup();

    static class SearchGroup {

        @CommandLine.Option(
                names = {"-o", "--option"},
                description = "@|bold What to search for: gene, chromosome or feature.|@"
        )
        String option;

        @CommandLine.Option(
                names = {"-v", "--value"},
                description = "@|bold Values to search for, separated by commas.|@%n" +
                        "@|bold Can be a gene name, chromosome number or feature type.|@",
                split = ","
        )
        String[] values;
    }

    @Override
    public void run() {
        try {
            GTFFunctions gtf = new GTFFunctions(input.input_file);

            for (String value : Search.values) {
                gtf.findValues(Search.option, value);
            }

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}