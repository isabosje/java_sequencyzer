package nl.bioinf.dsequencyzer;

import picocli.CommandLine;
import java.io.IOException;

@CommandLine.Command(
        name = "RegionCommand",
        description = "Gives region")
public class RegionCommand implements Runnable {

    @CommandLine.Option(
            names = {"-i"},
            description = "input file")
    String input_file;

    @CommandLine.Option(
            names = {"-chr"},
            description = "give chromosome")
    String chromosome;

    @CommandLine.Option(
            names = {"-s"},
            description = "give start")
    String start;

    @CommandLine.Option(
            names = {"-e"},
            description = "give end")
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
