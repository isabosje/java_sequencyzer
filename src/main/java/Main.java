import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String user_input = args[0];

        if (user_input.endsWith(".gtf")) {
            String gtf_option = args[1];

            String result = ReadGTF.getGTFPath(user_input, gtf_option);
            System.out.println(gtf_option + ": " + result);
        }

        // blast-functie wordt aangeroepen
        else if (user_input.endsWith(".blast")) {
            String blast_option = args[1];

            String result = ReadBLAST.readBlast(user_input, blast_option);
            System.out.println(blast_option + ":  " + result);
        }
        // rest BLAST afhandeling

        else {
            String sequence_option = args[1];

            // Roep getLength functie aan met sequentie meegegeven.
            if (sequence_option.equals("length")) {
                int length = SequenceFunctions.getLength(user_input);
                System.out.println("Length: " + length);
            }

            // Roep getGCPercentage aan en geef sequentie ook mee.
            else if (sequence_option.equals("gc")) {
                double gc_percentage = SequenceFunctions.getGCPercentage(user_input);

                if (gc_percentage == -1) {
                    System.out.println("Invalid sequence. A sequence can only consist of A, C, T, G and U nucleotides.");
                } else {
                    System.out.println("GC percentage: " + gc_percentage);
                }
            }
                // Roep getStartStop aan.
            else if (sequence_option.equals("startstop")) {
                    int startStop = SequenceFunctions.getStartStop(user_input);
                    System.out.println("Start/stop position: " + startStop);
            }
        }
    }
}