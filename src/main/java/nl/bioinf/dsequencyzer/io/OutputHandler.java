package nl.bioinf.dsequencyzer.io;

import nl.bioinf.dsequencyzer.command.OutputCommand;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Handles output operations by writing results to the terminal,
 * an output file or both, depending on the selected output mode.
 */

public class OutputHandler implements AutoCloseable {

    private final OutputCommand.OutputMode outputMode;
    private BufferedWriter writer;

    private static final Logger logger =
            LogManager.getLogger(OutputHandler.class);

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

        // Add .gtf if the output file does not have this extension
        if (!outputFile.endsWith(".gtf")) {
            outputFile += ".gtf";
        }

        // Put the output file in the output dir
        Path outputPath = Path.of(outputFile);

        if (outputPath.getParent() == null) {
            outputPath = Path.of("output", outputFile);
        }

        Path parentDirectory = outputPath.getParent();

        if (parentDirectory != null) {
            Files.createDirectories(parentDirectory);
        }

        writer = new BufferedWriter(new FileWriter(outputPath.toFile()));
    }

    // Write GTF headers
    public void writeHeaders(List<String> headers)
            throws IOException {

        if (writer != null) {
            for (String header : headers) {
                writer.write(header);
                writer.newLine();
            }
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