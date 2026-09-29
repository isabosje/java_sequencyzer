package nl.bioinf.dsequencyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FastaFunctions {

    private String fasta;

    public FastaFunctions(String input_file) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(input_file));

        String line;
        fasta = "";

        while ((line = reader.readLine()) != null) {

            // Skip FASTA header
            if (!line.startsWith(">")) {
                fasta += line;
            }
        }

        reader.close();
    }

    /**
     * Returns if all nucleotides in a sequence are valid nucleotides by using a boolean.
     *
     * @return is_valid_nucleotide valid nucleotides will return true, otherwise it will return false
     */
    public boolean isValidNucleotide() {

        boolean is_valid_nucleotide = true;

        for (int i = 0; i < fasta.length(); i++) {

            char nucleotide = fasta.charAt(i);

            if (nucleotide != 'A' && nucleotide != 'a' &&
                    nucleotide != 'C' && nucleotide != 'c' &&
                    nucleotide != 'G' && nucleotide != 'g' &&
                    nucleotide != 'T' && nucleotide != 't' &&
                    nucleotide != 'U' && nucleotide != 'u') {

                is_valid_nucleotide = false;
            }
        }

        return is_valid_nucleotide;
    }

    /**
     * Returns the length of the sequence.
     *
     * @return length of the sequence
     */
    public int getLength() {

        return fasta.length();
    }

    /**
     * Returns the percentage of G and C nucleotides.
     *
     * @return gc_percentage the percentage of G and C nucleotides
     */
    public double getGCPercentage() {

        double gc_bases = 0;

        for (int i = 0; i < fasta.length(); i++) {

            char nucleotide = fasta.charAt(i);

            if (nucleotide == 'G' || nucleotide == 'g') {
                gc_bases++;
            }

            if (nucleotide == 'C' || nucleotide == 'c') {
                gc_bases++;
            }
        }

        return gc_bases / fasta.length() * 100;
    }

    /**
     * Returns the first 10 lines of the FASTA file.
     *
     * @throws IOException if the file cannot be read
     */
    public void getHeader() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(fasta));

        String line;
        int lineNumber = 0;

        while ((line = reader.readLine()) != null && lineNumber < 10) {

            System.out.println(line);

            lineNumber++;
        }

        reader.close();
    }
}