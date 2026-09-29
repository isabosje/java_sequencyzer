package nl.bioinf.dsequencyzer;

/**
 * Provides fasta-functions for sequence
 */
public class FastaFunctions {

    private String sequence;

    /**
     * Creates a FastaFunctions object for a nucleotide sequence.
     *
     * @param sequence nucleotide sequence
     */
    public FastaFunctions(String sequence) {
        this.sequence = sequence;
    }

    /**
     * Checks whether the sequence contains valid nucleotides.
     *
     * @return true if the sequence only contains A, C, G, T or U (or lowercase)
     */
    public boolean isValidNucleotide() {
        return sequence.matches("[ACGTUacgtu]+");
    }

    /**
     * Measures the length of given sequence.
     *
     * @return sequence length in int
     */
    public int getLength() {
        return sequence.length();
    }

    /**
     * Calculates the GC percentage of the sequence.
     *
     * @return GC percentage in %
     */
    public double getGCPercentage() {

        int gc = 0;

        for (char nucleotide : sequence.toCharArray()) {

            if (nucleotide == 'G' || nucleotide == 'g' ||
                    nucleotide == 'C' || nucleotide == 'c') {
                gc++;
            }
        }

        return (double) gc / sequence.length() * 100; // calculation to percentage
    }
}