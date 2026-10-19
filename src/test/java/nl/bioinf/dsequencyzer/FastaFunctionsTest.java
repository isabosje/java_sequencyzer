package nl.bioinf.dsequencyzer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FastaFunctionsTest {

    @Test
    void readSequence() {
    }


    /*
     * Tests whether a given sequence is valid.
     *
     * Expected: ERROR --> invalid sequence
     */
    @Test
    void isValidNucleotide() {
        FastaFunctions invalid = new FastaFunctions("BBBGCTATC");
        FastaFunctions valid = new FastaFunctions("CGGCCCTATC");

        assertTrue(valid.isValidNucleotide());
        assertFalse(invalid.isValidNucleotide());
    }

    /*
     * Simepele eerste test voor het controleren van de getLength()
     *
     * Verwacht: 4 --> crasht bij meer of minder
     */
    @Test
    void getLength() {
        FastaFunctions fasta = new FastaFunctions("ATUGGCG");

        assertEquals(7, fasta.getLength());
    }

    @Test
    void getGCPercentage() {
        FastaFunctions fasta = new FastaFunctions("ATCGCUUCTCTCG");
        assertTrue(fasta.getGCPercentage() >= 0);
        assertTrue(fasta.getGCPercentage() <= 100);
    }

    @Test
    void getHeader() {
    }
}