package nl.bioinf.dsequencyzer;

public class GCCommand {

    public void run(String sequence) {

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
