package nl.bioinf.dsequencyzer;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.IOException;

@Command(
        name = "--region",
        description = "Retrieves the region on a given chromosome based on the start and end points.")
public class RegionCommand implements Runnable {

    @Option(
            names = {"-i, --input"},
            description = "Input a valid GTF file",
    required = true)
    String input_file;

    @Option(
            names = {"-chr"},
            description = "Add a chromosome and its number" +
                    "Note it the following way: 'chr1', chr2, etc.")
    String chromosome;

    @Option(
            names = {"-s"},
            description = "Add a start point" +
    "By default, the numbers go up to millions.")
    String start;

    @Option(
            names = {"-e"},
            description = "Add an end point" +
                    "By default, the numbers go up to millions.")
    String end;

    @Override
    public void run() {

        try {

            GTFFunctions gtf = new GTFFunctions(input_file);

            gtf.findRegion(chromosome, start, end);

        } catch (IOException e) {

            System.out.println("Error reading GTF file.");

        }
    }
}
