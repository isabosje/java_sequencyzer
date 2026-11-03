package nl.bioinf.dsequencyzer.command;

import picocli.CommandLine.Option;

public class InputCommand {

    @Option(
            names = {"-i", "--input"},
            // The @|bold is used to make --help more plain
            description = "@|bold Input a valid GTF or FASTA file.|@",
            required = true
    )
    String inputFile;
}