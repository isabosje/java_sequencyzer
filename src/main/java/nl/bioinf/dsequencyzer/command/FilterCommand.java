package nl.bioinf.dsequencyzer.command;

import nl.bioinf.dsequencyzer.GTFFunctions;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Option;

import java.io.IOException;

@Command(
        name = "filter",
        // The @|bold is used to make --help more plain
        description = "@|bold Filters GTF annotations using multiple conditions.|@"
)
public class FilterCommand implements Runnable {

    // These ArgGroups change --help by sorting them
    @ArgGroup(
            validate = false,
            heading = "%n@|bold,blue Input:|@%n",
            order = 1
    )
    InputCommand input = new InputCommand();

    @Mixin
    VerboseCommand verbose = new VerboseCommand();

    @ArgGroup(
            validate = false,
            heading = "%n@|bold,blue Output:|@%n",
            order = 2
    )
    OutputCommand output = new OutputCommand();

    @ArgGroup(
            validate = false,
            heading = "%n@|bold,blue Search:|@%n",
            order = 3
    )
    SearchGroup search = new SearchGroup();

    static class SearchGroup {
        @Option(
                names = {"-f", "--feature"},
                description = "Filter by feature, example: exon"
        )
        String feature = "";

        // Check toevoegen gebruiker

        @Option(
                names = {"-chr", "--chromosome"},
                description = "Filter by chromosome, example: chr1"
        )
        String chromosome = "";

        @Option(
                names = {"-s", "--strand"},
                description = "Filter by strand: + or -"
        )
        String strand = "";
    }

    @Override
    public void run() {
        verbose.run();
        // Catch exception while reading file
        try {
            GTFFunctions gtf = new GTFFunctions(input.inputFile);

            gtf.filterValues(
                    search.feature,
                    search.chromosome,
                    search.strand,
                    output.outputMode,
                    output.outputFile
            );

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}
