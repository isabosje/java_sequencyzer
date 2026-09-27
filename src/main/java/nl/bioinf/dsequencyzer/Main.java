import java.io.File;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "DSequencyzer")
public class Main implements Runnable {

    // Commandline arguments
    @Parameters
    String[] arguments;

    public static void main(String[] args) {
        new CommandLine(new Main()).execute(args);
    }

    @Override
    public void run() {

        // input_file = input file / sequence
        String input_file = arguments[1];

        // Check .gtf extension (maybe switch this with file object as well).
        if (input_file.endsWith(".gtf")) {

            String gtf_option = arguments[0];

            if (gtf_option.equals("summary")) {
                GTFFunctions.summaryCount(input_file);
            }

            else if (gtf_option.equals("find")) {

                String option = arguments[2];
                String value = arguments[3];

                GTFFunctions.findValue(input_file, option, value);
            }

            else if (gtf_option.equals("region")) {

                String chromosome = arguments[2];
                String start = arguments[3];
                String end = arguments[4];

                GTFFunctions.findRegion(input_file, chromosome, start, end);
            }
        }

        else {
            // Make new File object -> https://www.geeksforgeeks.org/java/file-isfile-method-in-java-with-examples/
            File file = new File(input_file);

            // Check if file is indeed a file.
            if (file.isFile()) {
                String file_option = arguments[0];

                if (file_option.equals("head")) {
                    FileFunctions.getHeader(input_file);
                }
            }

            else {
                String sequence_option = arguments[0];

                // Check if sequence consists of valid nucleotides.
                if (!SequenceFunctions.isValidNucleotide(input_file)) {
                    System.out.println("Invalid sequence. A sequence can only consist of A, C, T, G and U nucleotides.");
                }

                // Gets length of sequence by referring to getLength.
                else if (sequence_option.equals("length")) {
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

