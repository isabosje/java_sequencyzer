package nl.bioinf.dsequencyzer.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir; // for temporary .fasta files

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for reading FASTA files.
 */

class ReadFastaTest {

    @TempDir
    Path tempDir;

    /**
     * Tests whether a sequence is read correctly from a file.
     *
     * @throws IOException if the addition of text fails.
     */


    @Test
    void readSequence() throws IOException {
        Path fastaFile = tempDir.resolve("test.fasta");
        Files.writeString(fastaFile, ">sequence1\nACGT\nTGCA\n");

        assertEquals("ACGTTGCA", ReadFasta.readSequence(fastaFile.toString()));
    }


    /**
     * Tests whether a FASTA file without a header is rejected.
     *
     * @throws IOException if the absent header is accepted
     */
    @Test
    void WrongHeader() throws IOException {
        Path fastaFile = tempDir.resolve("invalid.fasta");
        Files.writeString(fastaFile, "ACGT\n");

        assertThrows(IllegalArgumentException.class,
                () -> ReadFasta.readSequence(fastaFile.toString()));
    }

    /**
     * Tests whether the requested no. of lines is read from a FASTA file.
     *
     * @throws IOException if the criteria is not met
     */
    @Test
    void readFirstLines() throws IOException {
        Path fastaFile = tempDir.resolve("test.fasta");
        Files.writeString(fastaFile, ">sequence1\nACGT\nTGCA\n");

        List<String> lines = ReadFasta.readFirstLines(fastaFile.toString(), 2);

        assertEquals(List.of(">sequence1", "ACGT"), lines);
    }
}
