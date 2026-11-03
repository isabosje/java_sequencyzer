package nl.bioinf.dsequencyzer.command;

import picocli.CommandLine.Option;

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

    public enum OutputMode {
        TERMINAL,
        FILE,
        BOTH
    }
}
