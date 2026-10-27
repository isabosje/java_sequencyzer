package nl.bioinf.dsequencyzer.command;
import nl.bioinf.dsequencyzer.GTFFunctions;
import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(
        name = "gene",
        // The @|bold is used to make --help more plain
        description = "@|bold Filters GTF annotations using multiple conditions.|@"
)
public class GeneCommand implements Runnable {

    // These ArgGroups change --help by sorting them
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
                names = {"-g", "--gene"},
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
        // Catch exception while reading file
        try {
            GTFFunctions gtf = new GTFFunctions(input.inputFile);

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
