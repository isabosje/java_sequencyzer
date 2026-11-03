package nl.bioinf.dsequencyzer.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputHandler {

    private static final Logger logger =
            LogManager.getLogger(InputHandler.class);

    /**
     * Determines the type of input.
     *
     * @param input file path or manually entered sequence
     * @return sequence, gtf, sequence_file or unknown
     */
    public String getInputType(String input) {

        logger.info("Trying to decide on your input type.");

        // Manually entered DNA/RNA sequence
        if (input.matches("[ACGTUacgtu]+")) {
            logger.info("Successfully found a sequence.");
            return "sequence";
        }

        String lowerInput = input.toLowerCase();

        // GTF file
        if (lowerInput.endsWith(".gtf")) {
            logger.info("Successfully found a GTF file.");
            return "gtf";
        }

        // FASTA file
        if (lowerInput.endsWith(".fasta")
                || lowerInput.endsWith(".fa")
                || lowerInput.endsWith(".fna")) {

            logger.info("Successfully found a sequence file.");
            return "sequence_file";
        }

        logger.warn("Could not find a valid input type.");
        return "unknown";
    }
}