public class SequenceFunctions {

    // Deze functie gebruikt .length om de lengte van de sequentie terug te geven.
    public static int getLength(String sequence) {
        return sequence.length();
    }

    // Deze functie gebruikt een for en if statement om het GC percentage te berekenen en terug te geven.
    public static double getGCPercentage(String sequence) {
        double gc_bases = 0;

        for (int i = 0; i < sequence.length(); i++) {

            // Char commando pakt een character en onthoud deze.
            // Wordt hier gebruikt om G en C op te tellen in sequentie.
            if (sequence.charAt(i) == 'G') {
                gc_bases++;
            }

            if (sequence.charAt(i) == 'C') {
                gc_bases++;
            }
        }

        double gc_percentage = gc_bases / sequence.length() * 100;

        return gc_percentage;
    }
}