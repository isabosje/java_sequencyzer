public class SequenceFunctions {

    // Deze functie gebruikt .length om de lengte van de sequentie terug te geven.
    public static int getLength(String user_input) {

        return user_input.length();
    }

    // Deze functie gebruikt een for en if statement om het GC percentage te berekenen en terug te geven.
    public static double getGCPercentage(String user_input) {
        double gc_bases = 0;

        for (int i = 0; i < user_input.length(); i++) {

            // Char commando pakt een character en onthoud deze.
            // Wordt hier gebruikt om G en C op te tellen in sequentie.
            if (user_input.charAt(i) == 'G' || user_input.charAt(i) == 'g') {
                gc_bases++;
            }

            if (user_input.charAt(i) == 'C' || user_input.charAt(i) == 'c') {
                gc_bases++;
            }
        }

        double gc_percentage = gc_bases / user_input.length() * 100;

        return gc_percentage;
    }
}
