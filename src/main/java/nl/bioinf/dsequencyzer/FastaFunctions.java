package nl.bioinf.dsequencyzer;


/**
 * Method for reading and analysing of FASTA sequences
 */
public class FastaFunctions {

    private final String sequence;

    public FastaFunctions(String sequence) {
        this.sequence = sequence;
    }

    /**
     * Checks the validity of a DNA sequence
     *
     * @return true if the sequence is correct
     */
    public boolean isValidNucleotide() {
        return sequence != null
                && !sequence.isEmpty()
                && sequence.matches("[ACGTUacgtu]+");
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

}