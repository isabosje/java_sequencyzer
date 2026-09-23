import java.io.IOException;
import java.io.File;

public class Main {
    public static void main(String[] args) throws IOException {
        // input_file = input file / sequence
        String input_file = args[1];

        // Check .gtf extension (maybe switch this with file object as well).
        if (input_file.endsWith(".gtf")) {
            String feature = args[2];

            int count = GTFFunctions.countFeatures(input_file, feature);
            System.out.println("Number of " + feature + ": " + count);

            int gene_name = GTFFunctions.


        } else {
            // Make new File object -> https://www.geeksforgeeks.org/java/file-isfile-method-in-java-with-examples/
            File file = new File(input_file);

            // Check if file is indeed a file.
            if (file.isFile()) {
                String file_option = args[0];

                if (file_option.equals("head")) {
                    FileFunctions.getHeader(input_file);
                }

            } else {
                String sequence_option = args[0];

                // Check if sequence consists of valid nucleotides.
                if (!SequenceFunctions.isValidNucleotide(input_file)) {
                    System.out.println("Invalid sequence. A sequence can only consist of A, C, T, G and U nucleotides.");
                }

                // Gets length of sequence by referring to getLength.
                if (sequence_option.equals("length")) {
                    int length = SequenceFunctions.getLength(input_file);
                    System.out.println("Length: " + length);
                }

                // Prints strings after referring to getGCPercentage rule.
                else if (sequence_option.equals("gc")) {
                    double gc_percentage = SequenceFunctions.getGCPercentage(input_file);
                    System.out.println("GC_percentage: " + gc_percentage);

                }
            }
        }
    }
}
