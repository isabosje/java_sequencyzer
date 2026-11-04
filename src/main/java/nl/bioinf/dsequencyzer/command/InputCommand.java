package nl.bioinf.dsequencyzer.command;

import picocli.CommandLine.Option;

/**
 * Mixin command that defines the required input file option for subcommands.
 *
 * Accepted file types: GTF and FASTA
 */

public class InputCommand {
    /**
     * Path to the input GTF or FASTA file specified by the user.
     */

    @Option(
            names = {"-i", "--input"},
            // The @|bold is used to make --help more plain
            description = "@|bold Input a valid GTF or FASTA file.|@",
            required = true
    )
    String inputFile;
}