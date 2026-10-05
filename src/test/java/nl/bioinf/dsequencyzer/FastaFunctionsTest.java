package nl.bioinf.dsequencyzer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FastaFunctionsTest {

    @Test
    void readSequence() {
    }

    @Test
    void isValidNucleotide() {
    }

    /*
     * Simepele eerste test voor het controleren van de getLength()
     *
     * Verwacht: 4
     */
    @Test
    void getLength() {
        FastaFunctions fasta = new FastaFunctions("ATCG");

        assertEquals(4, fasta.getLength());
    }

    @Test
    void getGCPercentage() {
    }

    @Test
    void getHeader() {
    }
}