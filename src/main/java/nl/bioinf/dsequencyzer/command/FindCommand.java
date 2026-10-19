package nl.bioinf.dsequencyzer.command;

import nl.bioinf.dsequencyzer.GTFFunctions;
import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(
        name = "find",
        description = "@|bold Filters GTF annotations using multiple conditions.|@"
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
            heading = "%n@|bold,blue Output:|@%n",
            order = 2
    )
    OutputCommand output = new OutputCommand();

    @CommandLine.ArgGroup(
            validate = false,
            heading = "%n@|bold,blue Search:|@%n",
            order = 3
    )
    SearchGroup search = new SearchGroup();

    static class SearchGroup {

        @CommandLine.Option(
                names = {"-f", "--feature"},
                description = "Filter by feature, example: exon"
        )
        String feature = "";

        // Check toevoegen gebruiker

        @CommandLine.Option(
                names = {"-chr", "--chromosome"},
                description = "Filter by chromosome, example: chr1"
        )
        String chromosome = "";

        @CommandLine.Option(
                names = {"-s", "--strand"},
                description = "Filter by strand: + or -"
        )
        String strand = "";
    }

    @Override
    public void run() {
        try {
            GTFFunctions gtf = new GTFFunctions(input.input_file);

            gtf.findValues(
                    search.feature,
                    search.chromosome,
                    search.strand,
                    output.output_file
            );

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}
