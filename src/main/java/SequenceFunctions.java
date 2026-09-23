import java.io.IOException;

public class SequenceFunctions {

    /**
     * Returns if all nucleotides in a sequence are valid nucleotides by using a boolean.
     *
     * @param user_input - the given sequence
     * @return is_valid_nucleotide - valid nucleotides will return true, otherwise it will return false
     */
    public static boolean isValidNucleotide(String user_input) {

        boolean is_valid_nucleotide = true;

        // Loop over all nucleotides / elements
        for (int i = 0; i < user_input.length(); i++) {

            char nucleotide = user_input.charAt(i);

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
         * @param user_input - the given sequence
         * @return user_input.length - the length of the given DNA sequence
         */
        public static int getLength (String user_input){

            return user_input.length();
        }

        /**
         * Returns the percentage of G and C nucleotides by using a for-loop and if-statements.
         *
         * @param user_input - the given sequence
         * @return gc_percentage - the percentage of G and C nucleotides
         */
        public static double getGCPercentage (String user_input){
            double gc_bases = 0;

            for (int i = 0; i < user_input.length(); i++) {

                char nucleotide = user_input.charAt(i);

                if (nucleotide == 'G' || nucleotide == 'g') {
                    gc_bases++;
                }

                if (nucleotide == 'C' || nucleotide == 'c') {
                    gc_bases++;
                }
            }

            return gc_bases / user_input.length() * 100;
        }
    }
