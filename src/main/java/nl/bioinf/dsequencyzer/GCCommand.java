package nl.bioinf.dsequencyzer;
import picocli.CommandLine;

@CommandLine.Command(
        name = "GCCommand",
        description = "Gives GC percentage")
public class GCCommand implements Runnable {
    @CommandLine.Option( names = {"-g", "--gc"},
            description = "Calculate the GC percentage of a sequence")
    String sequence;

    @Override
    public void run() {

        SequenceFunctions sequence_functions = new SequenceFunctions(sequence);

        if (!sequence_functions.isValidNucleotide()) {
            System.out.println("Invalid sequence. A sequence can only consist of A, C, T, G and U nucleotides.");
        }

        else {
            double gc_percentage = sequence_functions.getGCPercentage();
            System.out.println("GC_percentage: " + gc_percentage);
        }
    }
}
