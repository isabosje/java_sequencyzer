package nl.bioinf.dsequencyzer;

public class LengthCommand {

    public void run(String sequence) {

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
