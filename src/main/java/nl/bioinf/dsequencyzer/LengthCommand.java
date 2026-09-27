package nl.bioinf.dsequencyzer;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "length", description = "Returns the length of a nucleotide sequence.")
public class LengthCommand implements Runnable {

    @Parameters(index = "0", description = "Nucleotide sequence")
    String sequence;

    @Override
    public void run() {

        if (!SequenceFunctions.isValidNucleotide(sequence)) {
            System.out.println("Invalid sequence. A sequence can only consist of A, C, T, G and U nucleotides.");
        }

        else {
            int length = SequenceFunctions.getLength(sequence);
            System.out.println("Length: " + length);
        }
    }
}
