import java.io.IOException;
import java.io.File;

public class Main {
    public static void main(String[] args) throws IOException {
        // user_input = input file / sequence
        String user_input = args[1];

        // Check .gtf extension (maybe switch this with file object as well).
        if (user_input.endsWith(".gtf")) {
            String gtf_option = args[0];

            String result = GTFFunctions.getGTFColumn(user_input, gtf_option);
            System.out.println(gtf_option + " : " + result);

        } else {
            // Make new File object -> https://www.geeksforgeeks.org/java/file-isfile-method-in-java-with-examples/
            File file = new File(user_input);

            // Check if file is indeed a file.
            if (file.isFile()) {
                String file_option = args[0];

                if (file_option.equals("head")) {
                    FileFunctions.getHeader(user_input);
                }

            } else {
                String sequence_option = args[0];

                // Check if sequence consists of valid nucleotides.
                if (!SequenceFunctions.isValidNucleotide(user_input)) {
                    System.out.println("Invalid sequence. A sequence can only consist of A, C, T, G and U nucleotides.");
                }

                // Gets length of sequence by referring to getLength.
                if (sequence_option.equals("length")) {
                    int length = SequenceFunctions.getLength(user_input);
                    System.out.println("Length: " + length);
                }

                // Prints strings after referring to getGCPercentage rule.
                else if (sequence_option.equals("gc")) {
                    double gc_percentage = SequenceFunctions.getGCPercentage(user_input);
                    System.out.println("GC_percentage: " + gc_percentage);

                }
            }
        }
    }
}
