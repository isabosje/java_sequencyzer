import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        // Probeer hier alle opties te laten zien als ze niks invullen
        // Soort --help
        if (args.length == 0 || args[0].equals("help")) {
            System.out.println("DSequencyzer -> GTF-files");
            System.out.println();
            System.out.println("Options:");
            System.out.println("seqname");
            System.out.println("source");
            System.out.println("feature");
            System.out.println("start");
            System.out.println("end");
            System.out.println("score");
            System.out.println("strand");
            System.out.println("frame");
            System.out.println("attributes");

            return;
        }

        String user_input = args[0];
        String type = args[1];

        if (user_input.endsWith(".gtf")) {
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
