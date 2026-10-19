package nl.bioinf.dsequencyzer.command;

import picocli.CommandLine;

public class OutputCommand {

    @CommandLine.Option(
            names = {"-o", "--output"},
            description = "@|bold Add an output file name|@"
    )
    String output_file;
}
