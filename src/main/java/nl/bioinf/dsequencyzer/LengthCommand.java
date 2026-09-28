package nl.bioinf.dsequencyzer;

public class LengthCommand {

    public static void run(String sequence) {

        if (!SequenceFunctions.isValidNucleotide(sequence)) {
            System.out.println("Invalid sequence. A sequence can only consist of A, C, T, G and U nucleotides.");
        }

        else {
            int length = SequenceFunctions.getLength(sequence);
            System.out.println("Length: " + length);
        }
    }
}
