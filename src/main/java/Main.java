import java.io.IOException;
import java.io.File;

public class Main {
    public static void main(String[] args) throws IOException {

        String user_input = args[1];

        File file = new File(user_input);

        if (user_input.endsWith(".gtf")) {
            String gtf_option = args[0];

            String result = GTFFunctions.getGTFColumn(user_input, gtf_option);
            System.out.println(gtf_option + ": " + result);
        }

        else if (file.isFile()) {
            String file_option = args[0];

            if (file_option.equals("head")) {
                FileFunctions.getHeader(user_input);
            }
        }

        else {
            String sequence_option = args[0];

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
                }
                else {
                    System.out.println("GC percentage: " + gc_percentage);
                }
            }
        }
    }
}