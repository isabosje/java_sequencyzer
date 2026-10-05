package nl.bioinf.dsequencyzer.command;

import nl.bioinf.dsequencyzer.FastaFunctions;
import nl.bioinf.dsequencyzer.io.InputHandler;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;

@Command(
        name = "fasta",
        description = "Analyse a FASTA sequence",
        mixinStandardHelpOptions = true
)
public class FastaCommand implements Runnable {

    @Option(
            names = {"-i", "--input"},
            description = "Input FASTA file or sequence",
            required = true
    )
    private String input;

    @Option(
            names = {"-l", "--length"},
            description = "Show sequence length"
    )
    private boolean length;

    @Option(
            names = {"-g", "--gc"},
            description = "Show GC percentage"
    )
    private boolean gc;

    @Option(
            names = {"-v", "--validate"},
            description = "Validate nucleotide sequence"
    )
    private boolean validate;

    @Option(
            names = {"--head"},
            description = "Show the first 10 lines of the file"
    )
    private boolean head;

    @Override
    public void run() {

        InputHandler inputHandler = new InputHandler();

        String inputType = inputHandler.getInputType(input);

        try {

            /*
             * HEAD
             *
             * Head works directly on the input file.
             */
            if (head) {

                if (inputType.equals("sequence")) {
                    System.out.println("Head can only be used with a file.");
                    return;
                }

                FastaFunctions.getHeader(input);
                return;
            }

            /*
             * Determine the sequence.
             */
            String sequence;

            if (inputType.equals("sequence")) {

                sequence = input;

            } else if (inputType.equals("sequence_file")) {

                sequence = FastaFunctions.readSequence(input);

            } else {

                System.out.println("Unknown input type.");
                return;
            }

            /*
             * Create FastaFunctions object.
             */
            FastaFunctions fasta = new FastaFunctions(sequence);

            /*
             * Validate sequence.
             */
            if (!fasta.isValidNucleotide()) {

                System.out.println("Invalid nucleotide sequence.");
                return;
            }

            /*
             * Perform requested operations.
             */
            if (validate) {
                System.out.println("Sequence is valid.");
            }

            if (length) {
                System.out.println("Length: " + fasta.getLength());
            }

            if (gc) {
                System.out.println(
                        "GC percentage: " + fasta.getGCPercentage() + "%"
                );
            }

        } catch (IOException e) {

            System.out.println(
                    "Could not read input file: " + e.getMessage()
            );
        }
    }
}