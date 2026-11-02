package nl.bioinf.dsequencyzer.io;

import nl.bioinf.dsequencyzer.command.OutputCommand;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class OutputHandler implements AutoCloseable {

    private final OutputCommand.OutputMode outputMode;
    private BufferedWriter writer;

    /**
     * Handles the output by printing it in the terminal, writing a file or both using an enum.
     *
     * @throws IOException if the file does not end with .gtf (is not a .gtf file)
     * @param outputMode using FILE, TERMINAL or BOTH, you can self bepeel how you want the output to show up.
     * @param outputFile name the output file or the standard name.
     */
    public OutputHandler(
            OutputCommand.OutputMode outputMode,
            String outputFile)
            throws IOException {

        this.outputMode = outputMode;

        // Create output file if the mode is FILE or BOTH
        if (outputMode == OutputCommand.OutputMode.FILE
                || outputMode == OutputCommand.OutputMode.BOTH) {

            // Create output directory if it does not exist
            Path outputPath = Path.of(outputFile);
            Path parentDirectory = outputPath.getParent();

            if (parentDirectory != null) {
                Files.createDirectories(parentDirectory);
            }

            writer = new BufferedWriter(new FileWriter(outputFile));
        }
    }

    // Handles the output
    public void write(String line)
            throws IOException {

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