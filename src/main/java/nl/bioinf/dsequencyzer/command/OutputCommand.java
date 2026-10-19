package nl.bioinf.dsequencyzer.command;

import picocli.CommandLine;

public class OutputCommand {

    @CommandLine.Option(
            names = {"-o", "--output"},
            // The @|bold is used to make --help more plain
            description = "@|bold Add an output file name|@"
    )
    String output_file;
}
