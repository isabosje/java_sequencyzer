package nl.bioinf.dsequencyzer.io;

import nl.bioinf.dsequencyzer.command.OutputCommand;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputHandler implements AutoCloseable {

    private final OutputCommand.OutputMode outputMode;
    private BufferedWriter writer;

    public OutputHandler(
            OutputCommand.OutputMode outputMode,
            String outputFile
    ) throws IOException {

        this.outputMode = outputMode;

        // Create output file if the mode is FILE or BOTH
        if (outputMode == OutputCommand.OutputMode.FILE
                || outputMode == OutputCommand.OutputMode.BOTH) {

            writer = new BufferedWriter(new FileWriter(outputFile));
        }
    }

    // Handles the output
    public void write(String line) throws IOException {

        // Print matching lines in the terminal
        if (outputMode == OutputCommand.OutputMode.TERMINAL
                || outputMode == OutputCommand.OutputMode.BOTH) {

            System.out.println(line);
        }

        // Write matching lines to the output file
        if (writer != null) {
            writer.write(line);
            writer.newLine();
        }
    }

    // Close output file
    @Override
    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}