package nl.bioinf.dsequencyzer;

public class GCCommand {

    public static void run(String sequence) {

        if (!SequenceFunctions.isValidNucleotide(sequence)) {
            System.out.println("Invalid sequence. A sequence can only consist of A, C, T, G and U nucleotides.");
        }

        else {
            double gc_percentage = SequenceFunctions.getGCPercentage(sequence);
            System.out.println("GC_percentage: " + gc_percentage);
        }
    }
}