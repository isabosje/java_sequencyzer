
package nl.bioinf.dsequencyzer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the FastaFunctions class.
 * Tests nucleotide validation, sequence length calculation
 * and GC percentage calculation.
 */
class FastaFunctionsTest {

    /**
     * Tests whether a valid DNA sequence is accepted.
     */
    @Test
    void DNASequence() {
        FastaFunctions fasta = new FastaFunctions("ACGTacgt");

        assertTrue(fasta.isValidNucleotide());
    }

    /**
     * Tests whether a valid RNA sequence is accepted.
     */
    @Test
    void RNASequence() {
        FastaFunctions fasta = new FastaFunctions("ACGU");

        assertTrue(fasta.isValidNucleotide());
    }

    /**
     * Tests whether a sequence containing invalid nucleotide
     * characters is rejected.
     */
    @Test
    void InvalidNucleotide() {
        FastaFunctions fasta = new FastaFunctions("ACGTXYZ");

        assertFalse(fasta.isValidNucleotide()); // passes because of "assertFalse"
    }

    /**
     * Tests whether the sequence length is calculated correctly.
     */
    @Test
    void SequenceLengthValidity() {
        FastaFunctions fasta = new FastaFunctions("ATGCC");

        assertEquals(5, fasta.getLength());
    }

    /**
     * Tests whether the GC percentage is calculated correctly
     * for a sequence containing four G/C nucleotides out of six.
     */
    @Test
    void GCPercentageValidity() {
        FastaFunctions fasta = new FastaFunctions("GGCCAT");

        assertEquals(100.0 * 4 / 6,
                fasta.getGCPercentage(), 0.001);
    }

    /**
     * Tests the boundary case where a sequence contains no
     * G or C nucleotides.
     */
    @Test
    void GCPercentage0() {
        FastaFunctions fasta = new FastaFunctions("ATAT");

        assertEquals(0.0, fasta.getGCPercentage(), 0.01);
    }

    /**
     * Tests the boundary case where every nucleotide is G or C.
     */
    @Test
    void GCPercentage100() {
        FastaFunctions fasta = new FastaFunctions("GGCC");

        assertEquals(100.0, fasta.getGCPercentage(), 0.01);
    }
}
