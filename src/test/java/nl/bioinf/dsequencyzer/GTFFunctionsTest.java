package nl.bioinf.dsequencyzer;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class GTFFunctionsTest {

    @Test
    void summaryCount() throws IOException {
        String inputFile = getClass()
                .getClassLoader()
                .getResource("generated_test.gtf")
                .getPath();

        GTFFunctions gtf = new GTFFunctions(inputFile);

        gtf.summaryCount();
    }

    @Test
    void findValue() throws IOException {
        String inputFile = getClass()
                .getClassLoader()
                .getResource("generated_test.gtf")
                .getPath();

        GTFFunctions gtf = new GTFFunctions(inputFile);

        gtf.findValues("exon", "chr1_test", "-", "output_test_find");
    }

    @Test
    void findRegion() throws IOException {
        String inputFile = getClass()
                .getClassLoader()
                .getResource("generated_test.gtf")
                .getPath();

        GTFFunctions gtf = new GTFFunctions(inputFile);

        gtf.findRegion("chr1_test", "1", "500", "output_test_region");
    }
}