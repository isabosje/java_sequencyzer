package nl.bioinf.dsequencyzer.command;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests for the FastaCommand commandline functionality
 */
class FastaCommandTest {

    /**
     * Tests whether the length of a nucleotide is correct.
     */
    @Test
    void sequenceLength() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));

        try {
            int exitCode = new CommandLine(new FastaCommand())
                    .execute("--input", "ACGT", "--length");

            assertEquals(0, exitCode);
            assertTrue(output.toString().contains("Length: 4"));
        } finally {
            System.setOut(originalOut);
        }
    }


    /**
     * Tests whether an invalid sequence is rejected.
     */
    @Test
    void invalidSequence() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));

        try {
            new CommandLine(new FastaCommand())
                    .execute("--input", "ACGX", "--validate");

            assertFalse(output.toString().contains("Invalid nucleotide sequence."));
        } finally {
            System.setOut(originalOut);
        }
    }
}
