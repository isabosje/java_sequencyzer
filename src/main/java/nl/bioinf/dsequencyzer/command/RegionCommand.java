package nl.bioinf.dsequencyzer.command;

import nl.bioinf.dsequencyzer.GTFFunctions;

import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.ArgGroup;

import java.io.IOException;


@Command(
        name = "region",
        // The @|bold is used to make --help more plain
        description = "%n@|bold Retrieves the region on a given chromosome based on the start and %n" +
                "end points.|@"
)
public class RegionCommand implements Runnable {

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
            heading = "%n@|bold,blue Region:|@%n",
            order = 3
    )
    RegionGroup Region = new RegionGroup();

    static class RegionGroup {

        @Option(
                names = {"-chr", "--chromosome"},
                description = "@|bold Add a chromosome and its number.|@%n" +
                        "@|bold Note it the following way: 'chr1', chr2, etc.|@"
        )
        String chromosome;

        @Option(
                names = {"-s", "--start"},
                description = "@|bold Add a start point.|@%n" +
                        "@|bold By default, the numbers go up to millions.|@"
        )
        String start;

        @Option(
                names = {"-e", "--end"},
                description = "@|bold Add an end point.|@"
        )
        String end;
    }

    @Override
    public void run() {
        verbose.run();

        // Catch exception while reading file
        try {
            GTFFunctions gtf = new GTFFunctions(input.inputFile);

            gtf.findRegion(
                    Region.chromosome,
                    Region.start,
                    Region.end,
                    output.outputFile,
                    output.outputMode
            );

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}