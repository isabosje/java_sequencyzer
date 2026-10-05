package nl.bioinf.dsequencyzer.io;

public class InputHandler {

    /**
     * Determines the type of input.
     *
     * @param input file path or manually entered sequence
     * @return sequence, gtf, sequence_file or unknown
     */
    public String getInputType(String input) {

        // Manually entered DNA/RNA sequence
        if (input.matches("[ACGTUacgtu]+")) {
            return "sequence";
        }

        String lowerInput = input.toLowerCase();

        // GTF file
        if (lowerInput.endsWith(".gtf")) {
            return "gtf";
        }

        // FASTA file
        if (lowerInput.endsWith(".fasta")
                || lowerInput.endsWith(".fa")
                || lowerInput.endsWith(".fna")) {

            return "sequence_file";
        }

        return "unknown";
    }
}