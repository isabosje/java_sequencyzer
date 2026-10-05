package nl.bioinf.dsequencyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Functions for reading and analysing FASTA sequences.
 */
public class FastaFunctions {

    private String sequence;

    public FastaFunctions(String sequence) {
        this.sequence = sequence;
    }

    /**
     * Reads a sequence from a FASTA file.
     * FASTA header lines are ignored.
     *
     * @param input path to FASTA file
     * @return sequence as String
     * @throws IOException when the file cannot be read
     */
    public static String readSequence(String input) throws IOException {

        StringBuilder sequence = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (!line.startsWith(">")) {
                    sequence.append(line.trim());
                }
            }
        }

        return sequence.toString();
    }

    /**
     * Checks whether all characters in the sequence
     * are valid DNA/RNA nucleotides.
     *
     * @return true if the sequence is valid
     */
    public boolean isValidNucleotide() {

        if (sequence == null || sequence.isEmpty()) {
            return false;
        }

        return sequence.matches("[ACGTUacgtu]+");
    }

    /**
     * Returns the length of the sequence.
     *
     * @return sequence length
     */
    public int getLength() {
        return sequence.length();
    }

    /**
     * Calculates the GC percentage.
     *
     * @return GC percentage
     */
    public double getGCPercentage() {

        if (sequence == null || sequence.isEmpty()) {
            return 0.0;
        }

        int gcBases = 0;

        for (char nucleotide : sequence.toCharArray()) {

            if (nucleotide == 'G' || nucleotide == 'g'
                    || nucleotide == 'C' || nucleotide == 'c') {

                gcBases++;
            }
        }

        return (double) gcBases / sequence.length() * 100;
    }

    /**
     * Prints the first 10 lines of a file.
     *
     * @param input path to input file
     * @throws IOException when the file cannot be read
     */
    public static void getHeader(String input) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null && lineNumber < 10) {

                System.out.println(line);
                lineNumber++;
            }
        }
    }
}