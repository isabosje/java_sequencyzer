package nl.bioinf.dsequencyzer.command;

import picocli.CommandLine;

public class InputCommand {

    @CommandLine.Option(
            names = {"-i", "--input"},
            // The @|bold is used to make --help more plain
            description = "@|bold Input a valid GTF or FASTA file.|@",
            required = true
    )
    String input_file;
}