package nl.bioinf.dsequencyzer;

public class SequenceFunctions {

    private String sequence;

    public SequenceFunctions(String sequence) {
        this.sequence = sequence;
    }

    /**
     * Returns if all nucleotides in a sequence are valid nucleotides by using a boolean.
     *
     * @return is_valid_nucleotide valid nucleotides will return true, otherwise it will return false
     */
    public boolean isValidNucleotide() {

        boolean is_valid_nucleotide = true;

        // Loop over all nucleotides / elements
        for (int i = 0; i < sequence.length(); i++) {

            char nucleotide = sequence.charAt(i);

            // Check if this can be done more efficiently.
            if (nucleotide != 'A' && nucleotide != 'a' &&
                    nucleotide != 'C' && nucleotide != 'c' &&
                    nucleotide != 'G' && nucleotide != 'g' &&
                    nucleotide != 'T' && nucleotide != 't' &&
                    nucleotide != 'U' && nucleotide != 'u') {

                // Returns false to main
                is_valid_nucleotide = false;
            }
        }

        return is_valid_nucleotide;
    }

    /**
     * Returns the length of a given sequence through the terminal.
     *
     * @return user_input.length the length of the given DNA sequence
     */
    public int getLength() {

        return sequence.length();
    }

    /**
     * Returns the percentage of G and C nucleotides by using a for-loop and if-statements.
     *
     * @return gc_percentage the percentage of G and C nucleotides
     */
    public double getGCPercentage() {

        double gc_bases = 0;

        for (int i = 0; i < sequence.length(); i++) {

            char nucleotide = sequence.charAt(i);

            if (nucleotide == 'G' || nucleotide == 'g') {
                gc_bases++;
            }

            if (nucleotide == 'C' || nucleotide == 'c') {
                gc_bases++;
            }
        }

        return gc_bases / sequence.length() * 100;
    }
}