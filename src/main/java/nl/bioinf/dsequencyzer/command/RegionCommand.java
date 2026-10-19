package nl.bioinf.dsequencyzer.command;

import nl.bioinf.dsequencyzer.GTFFunctions;
import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(
        name = "region",
        description = "%n@|bold Retrieves the region on a given chromosome based on the start and %n" +
                "end points.|@"
)
public class RegionCommand implements Runnable {

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
            heading = "%n@|bold,blue Region:|@%n",
            order = 3
    )
    RegionGroup Region;

    static class RegionGroup {

        @CommandLine.Option(
                names = {"-chr", "--chromosome"},
                description = "@|bold Add a chromosome and its number.|@%n" +
                        "@|bold Note it the following way: 'chr1', chr2, etc.|@"
        )
        String chromosome;

        @CommandLine.Option(
                names = {"-s", "--start"},
                description = "@|bold Add a start point.|@%n" +
                        "@|bold By default, the numbers go up to millions.|@"
        )
        String start;

        @CommandLine.Option(
                names = {"-e", "--end"},
                description = "@|bold Add an end point.|@"
        )
        String end;
    }

    @Override
    public void run() {

        try {
            GTFFunctions gtf = new GTFFunctions(input.input_file);

            gtf.findRegion(
                    Region.chromosome,
                    Region.start,
                    Region.end,
                    output.output_file
            );

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}