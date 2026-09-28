package nl.bioinf.dsequencyzer;
import picocli.CommandLine;

@CommandLine.Command(
        name = "LengthCommand",
        description = "Gives length")

public class LengthCommand implements Runnable {
    @CommandLine.Option( names = {"-l", "--length"},
            description = "Give a sequence")
    String sequence;

    @Override
    public void run() {

        SequenceFunctions sequence_functions = new SequenceFunctions(sequence);

        if (!sequence_functions.isValidNucleotide()) {
            System.out.println("Invalid sequence. A sequence can only consist of A, C, T, G and U nucleotides.");
        }

        else {
            int length = sequence_functions.getLength();
            System.out.println("Length: " + length);
        }
    }
}
