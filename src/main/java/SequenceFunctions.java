import javax.crypto.AEADBadTagException;

public class SequenceFunctions {

    // Deze functie gebruikt .length om de lengte van de sequentie terug te geven.
    public static int getLength(String user_input) {

        return user_input.length();
    }

    // Deze functie gebruikt een for en if statement om het GC percentage te berekenen en terug te geven.
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

            // Char commando pakt een character en onthoud deze.
            // Wordt hier gebruikt om G en C op te tellen in sequentie.
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

    public static int getStartStop(String dna_sequence)  {
        int startPosition = dna_sequence.indexOf("ATG");

        if (startPosition == -1) {
            return -1;
        }
        for (int i = startPosition; i < dna_sequence.length() -2; i += 3) {
            String codon = dna_sequence.substring(i, i + 3);
            if (codon.equals("TGA") ||
            codon.equals("TAG") ||
            codon.equals("TAA")) {
                return i;
            }
        }

    return -1

    }
}
