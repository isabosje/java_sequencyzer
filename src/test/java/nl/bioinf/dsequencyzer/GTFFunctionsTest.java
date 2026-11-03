package nl.bioinf.dsequencyzer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import nl.bioinf.dsequencyzer.command.OutputCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GTFFunctionsTest {

    @TempDir
    Path tempDir;

    @Test
    void summaryCount() throws IOException {
        GTFFunctions gtf = new GTFFunctions("src/test/resources/generated_test.gtf");

        Map<String, Integer> summary = gtf.summaryCount();

        assertEquals(100, summary.get("total_lines"));
        assertEquals(2, summary.get("chromosomes"));
        assertEquals(20, summary.get("genes"));
    }

    @Test
    void filterValues() throws IOException {
        GTFFunctions gtf = new GTFFunctions("src/test/resources/generated_test.gtf");

        Path output = tempDir.resolve("filter.gtf");

        gtf.filterValues(
                "exon",
                "chr1_test",
                "+",
                OutputCommand.OutputMode.FILE,
                output.toString()
        );

        assertEquals(18, Files.readAllLines(output).size());
    }

    @Test
    void findRegion() throws IOException {
        GTFFunctions gtf = new GTFFunctions("src/test/resources/generated_test.gtf");

        Path output = tempDir.resolve("region.gtf");

        gtf.findRegion(
                "chr1_test",
                "1",
                "81",
                output.toString(),
                OutputCommand.OutputMode.FILE
        );

        assertEquals(2, Files.readAllLines(output).size());
    }

    @Test
    void chromosomeCount() throws IOException {
        GTFFunctions gtf = new GTFFunctions("src/test/resources/generated_test.gtf");

        Map<String, Integer> chromosomes = gtf.chromosomeCount();

        assertEquals(60, chromosomes.get("chr1_test"));
        assertEquals(40, chromosomes.get("chr2_test"));
    }
}