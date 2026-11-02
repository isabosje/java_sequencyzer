package nl.bioinf.dsequencyzer;

import org.junit.jupiter.api.Test;

import nl.bioinf.dsequencyzer.model.Gene;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GTFFunctionsTest {

    @Test
    void summaryCount() throws IOException {
        GTFFunctions gtf = new GTFFunctions("src/test/resources/generated_test.gtf");

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        gtf.summaryCount();

        System.setOut(System.out);

        String result = output.toString();

        assertTrue(result.contains("Total annotation lines: 100"));
        assertTrue(result.contains("Unique chromosomes: 2"));
        assertTrue(result.contains("Genes: 20"));
        assertTrue(result.contains("gene : 20"));
        assertTrue(result.contains("transcript : 20"));
        assertTrue(result.contains("exon : 60"));
    }

    @Test
    void parseGTF() throws IOException {
        GTFFunctions gtf = new GTFFunctions("src/test/resources/generated_test.gtf");

        Map<String, Gene> genes = gtf.parseGTF();

        assertEquals(20, genes.size());
        assertNotNull(genes.get("gene001"));
        assertNotNull(genes.get("gene020"));
    }


}