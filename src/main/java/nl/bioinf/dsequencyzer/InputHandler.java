package nl.bioinf.dsequencyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Identifies different input files and returns the correct type
 */
public class InputHandler {
    /**
     * Determines the input filetype
     *
     * @param input file / path or manual typed sequence
     * @return input type: sequence, gtf, sequence_type (.fa/.fasta/.fna) or unknown
     * @throws IOException
     */

    public String getInputType(String input) throws IOException {

        if (input.matches("[ACGTUacgtu]+")) {
            return "sequence";
        }

        if (input.endsWith(".gtf")) {
            return "gtf";
        }

        if (input.endsWith(".fasta") ||
                input.endsWith(".fa") ||
                input.endsWith(".fna")) {
            return "sequence_file";
        }

        return "unknown";
    }

    /**
     * Reads a sequence from a file
     * FASTA header lines are ignored
     *
     * @param input path to the sequence file
     * @return sequence as String
     */
    public String readSequence(String input) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(input));

        StringBuilder sequence = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {

            if (!line.startsWith(">")) { // signature .fasta file
                sequence.append(line.trim());
            }
        }

        reader.close();

        return sequence.toString();
    }
}