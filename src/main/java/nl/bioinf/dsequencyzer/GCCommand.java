package nl.bioinf.dsequencyzer;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "gc", description = "Returns the GC percentage of a nucleotide sequence.")
public class GCCommand implements Runnable {

    @Parameters(index = "0", description = "Nucleotide sequence")
    String sequence;

    @Override
    public void run() {

        if (!SequenceFunctions.isValidNucleotide(sequence)) {
            System.out.println("Invalid sequence. A sequence can only consist of A, C, T, G and U nucleotides.");
        }

        else {
            double gc_percentage = SequenceFunctions.getGCPercentage(sequence);
            System.out.println("GC_percentage: " + gc_percentage);
        }
    }
}