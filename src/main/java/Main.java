import java.io.IOException;
import java.io.File;

public class Main {
    public static void main(String[] args) throws IOException {
        // user_input = input file / sequence
        String user_input = args[1];

        // Check .gtf extention (maybe switch this with file object as well).
        if (user_input.endsWith(".gtf")) {
            String gtf_option = args[0];

            String result = GTFFunctions.getGTFColumn(user_input, gtf_option);
            System.out.println(gtf_option + " : " + result);
        }

        else {
            // Make new File object.
            File file = new File(user_input);

            // Check if file is indeed a file.
            if (file.isFile()) {
                String file_option = args[0];

                if (file_option.equals("head")) {
                    FileFunctions.getHeader(user_input);
                }
            }


        else {
            String sequence_option = args[0];

            // Gets length of sequence by refering to getLength.
            if (sequence_option.equals("length")) {
                int length = SequenceFunctions.getLength(user_input);
                System.out.println("Length: " + length);
            }

            // Prints strings after refering to getGCPercentage rule.
            else if (sequence_option.equals("gc")) {
                double gc_percentage = SequenceFunctions.getGCPercentage(user_input);

                // -1 will be returned if sequence is not valid.
                if (gc_percentage == -1) {
                    System.out.println("Invalid sequence. A sequence can only consist of A, C, T, G and U nucleotides.");
                }
                // Else continue with gc-percentage.
                else {
                    System.out.println("GC percentage: " + gc_percentage);
                }
            }
        }
    }
}