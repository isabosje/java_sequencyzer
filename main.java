import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String user_input = args[0];
        String type = args[1];

        if (user_input.endsWith(".gtf")) {
            ReadGTF.getGTFPath(user_input, type);

            String result = ReadGTF.getGTFPath(user_input, type);
            System.out.println(type + ": " + result);
        }
        else    {
            // Roep getLength functie aan met sequentie meegegeven.
            int length = SequenceFunctions.getLength(user_input);

            // Roep getGCPercentage aan en geef sequentie ook mee.
            double gc_percentage = SequenceFunctions.getGCPercentage(user_input);

            // Moet wel even netjes natuurlijk.
            System.out.println("Sequence: " + user_input);
            System.out.println("Length: " + length);
            System.out.println("GC percentage: " + gc_percentage);
        }
    }
}
