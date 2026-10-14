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
            heading = "%n@|bold,blue Search:|@%n",
            order = 2
    )
    SearchGroup search = new SearchGroup();

    static class SearchGroup {

        @CommandLine.Option(
                names = {"--feature"},
                description = "Filter by feature, example: exon"
        )
        String feature = "";

        @CommandLine.Option(
                names = {"--chromosome"},
                description = "Filter by chromosome, example: chr1"
        )
        String chromosome = "";

        @CommandLine.Option(
                names = {"--strand"},
                description = "Filter by strand: + or -"
        )
        String strand = "";

        @CommandLine.Option(
                names = {"-o", "--output"},
                description = "Name of the output GTF file"
        )
        String output_file = "filtered.gtf";
    }

    @Override
    public void run() {
        try {
            GTFFunctions gtf = new GTFFunctions(input.input_file);

            gtf.findValues(
                    search.feature,
                    search.chromosome,
                    search.strand,
                    search.output_file
            );

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}
