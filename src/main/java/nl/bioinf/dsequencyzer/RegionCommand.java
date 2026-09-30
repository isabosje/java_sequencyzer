package nl.bioinf.dsequencyzer;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.ArgGroup;
import java.io.IOException;

@Command(
        name = "--region",
        description = "%n@|bold Retrieves the region on a given chromosome based on the start and end points.|@%n")
public class RegionCommand implements Runnable {

    @ArgGroup(
            validate = false,
            heading = "%n@|bold,cyan Input:|@%n",
            order = 1
    )
    InputGroup Input;

    @ArgGroup(
            validate = false,
            heading = "%n@|bold,cyan Search:|@%n",
            order = 1
    )
    RegionGroup Region;

    static class InputGroup {

        @Option(
                names = {"-i, --input"},
                description = "Input a valid GTF file",
                required = true)
        String input_file;
    }

    static class RegionGroup {

        @Option(
                names = {"-chr, --chromosome"},
                description = "Add a chromosome and its number" +
                        "Note it the following way: 'chr1', chr2, etc.")
        String chromosome;

        @Option(
                names = {"-s, --start"},
                description = "Add a start point" +
                        "By default, the numbers go up to millions.")
        String start;

        @Option(
                names = {"-e, --end"},
                description = "Add an end point" +
                        "By default, the numbers go up to millions.")
        String end;

    }

    @Override
    public void run() {

        try {

            GTFFunctions gtf = new GTFFunctions(Input.input_file);

            gtf.findRegion(Region.chromosome, Region.start, Region.end);

        } catch (IOException e) {

            System.out.println("Error reading GTF file.");

        }
    }
}
