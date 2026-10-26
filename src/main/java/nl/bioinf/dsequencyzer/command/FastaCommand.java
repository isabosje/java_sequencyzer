package nl.bioinf.dsequencyzer.command;

import nl.bioinf.dsequencyzer.FastaFunctions;
import nl.bioinf.dsequencyzer.io.InputHandler;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;

/**
 * Commandline command for analyzing FASTA files or a nucleotide sequence
 * This consists of: sequence length, gc percentage, validation
 * or displaying the header.
 *
 * v0.1
 * Date 05/10/2026
 */
@Command(
        name = "fasta",
        description = "Analyse a FASTA sequence",
        mixinStandardHelpOptions = true
)
public class FastaCommand implements Runnable {

    /**
     * Input either FASTA file or nucleotide sequence
     */
    @CommandLine.Mixin
    InputCommand input = new InputCommand();

    /**
     * Displays the sequence length if requested
     */
    @Option(
            names = {"-l", "--length"},
            description = "Show sequence length"
    )
    private boolean length;

    /**
     * Displays the GC percentage if requested
     */
    @Option(
            names = {"-g", "--gc"},
            description = "Show GC percentage"
    )
    private boolean gc;

    /**
     * Validates whether a sequence is valid or not
     */
    @Option(
            names = {"-v", "--validate"},
            description = "Validate nucleotide sequence"
    )
    private boolean validate;

    /**
     * Shows the first 10 lines of the input file
     */
    @Option(
            names = {"--head"},
            description = "Show the first 10 lines of the file"
    )
    private boolean head;

    @Override
    public void run() {

        InputHandler inputHandler = new InputHandler();

        String inputType = inputHandler.getInputType(input.inputFile);

        try {

            if (head) {

                if (inputType.equals("sequence")) {
                    System.out.println("Head can only be used with a file.");
                    return;
                }

                FastaFunctions.getHeader(input.inputFile);
                return;
            }

            /*
             * Determine the sequence.
             */
            String sequence;

            if (inputType.equals("sequence")) {

                sequence = input.inputFile;

            } else if (inputType.equals("sequence_file")) {

                sequence = FastaFunctions.readSequence(input.inputFile);

            } else {

                System.out.println("Unknown input type.");
                return;
            }

            /*
             * Create FastaFunctions object.
             */
            FastaFunctions fasta = new FastaFunctions(sequence);

            /*
             * Validate sequence
             */
            if (!fasta.isValidNucleotide()) {

                System.out.println("Invalid nucleotide sequence.");
                return;
            }

            /*
             * Perform requested operations
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