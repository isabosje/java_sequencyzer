package nl.bioinf.dsequencyzer.command;

import picocli.CommandLine;

public class InputCommand {

    @CommandLine.Option(
            names = {"-i", "--input"},
            description = "@|bold Input a valid GTF or FASTA file.|@",
            required = true
    )
    String input_file;
}