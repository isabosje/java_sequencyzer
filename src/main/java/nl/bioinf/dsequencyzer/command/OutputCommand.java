package nl.bioinf.dsequencyzer.command;

import picocli.CommandLine.Option;

/**
 * Mixin command that defines the output options for subcommands.
 */

public class OutputCommand {

    @Option(
            names = {"-o", "--output"},
            description = "@|bold Add an output file name|@",
            defaultValue = "output.gtf"
    )
    String outputFile = "output.gtf";

    @Option(
            names = {"--output-mode"},
            description = "Output mode: TERMINAL, FILE or BOTH.",
            defaultValue = "TERMINAL"
    )
    OutputMode outputMode = OutputMode.TERMINAL;

    /**
     * Defines the available output modes.
     */
    public enum OutputMode {
        TERMINAL,
        FILE,
        BOTH
    }
}
