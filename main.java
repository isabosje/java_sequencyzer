public class Main {
    public static void main(String[] args) {

        String sequence = args[0];

        // Roep getLength functie aan met sequentie meegegeven.
        int length = SequenceFunctions.getLength(sequence);

        // Roep getGCPercentage aan en geef sequentie ook mee.
        double gc_percentage = SequenceFunctions.getGCPercentage(sequence);

        // Moet wel even netjes natuurlijk.
        System.out.println("Sequence: " + sequence);
        System.out.println("Length: " + length);
        System.out.println("GC percentage: " + gc_percentage);
    }
}