import java.io.IOException;

public class SequenceFunctions {

    /**
     * Returns the length of a given sequence through the terminal.
     *
     * @param user_input - the given sequence
     * @return user_input.length - the length of the given DNA sequence
     */
    public static int getLength(String user_input) {

        return user_input.length();
    }

    /**
     * Returns the percentage of G and C nucleotides by using a for-loop and if-statements.
     *
     * @param user_input - the given sequence
     * @return gc_percentage - the percentage of G and C nucleotides
     */
    public static double getGCPercentage(String user_input) {
        boolean is_valid_nucleotide = true;

        double gc_bases = 0;

        for (int i = 0; i < user_input.length(); i++) {

            char nucleotide = user_input.charAt(i);

            if (nucleotide != 'A' && nucleotide != 'a' &&
                    nucleotide != 'C' && nucleotide != 'c' &&
                    nucleotide != 'G' && nucleotide != 'g' &&
                    nucleotide != 'T' && nucleotide != 't' &&
                    nucleotide != 'U' && nucleotide != 'u') {

                is_valid_nucleotide = false;
            }

            if (nucleotide == 'G' || nucleotide == 'g') {
                gc_bases++;
            }

            if (nucleotide == 'C' || nucleotide == 'c') {
                gc_bases++;
            }
        }
        if (!is_valid_nucleotide) {
            return -1;
        }

        double gc_percentage = gc_bases / user_input.length() * 100;

        return gc_percentage;
    }
}
