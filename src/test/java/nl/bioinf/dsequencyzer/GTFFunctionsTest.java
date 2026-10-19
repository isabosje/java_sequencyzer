package nl.bioinf.dsequencyzer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class GTFFunctionsTest {
    private String inputFile;

    @BeforeEach
    void setUp() {
        inputFile = getClass()
                .getClassLoader()
                .getResource("generated_test.gtf")
                .getPath();
    }

    @Test
    void summaryCount() throws IOException {

        GTFFunctions gtf = new GTFFunctions(inputFile);

        gtf.summaryCount();
    }

    @Test
    void findValue() throws IOException {

        GTFFunctions gtf = new GTFFunctions(inputFile);

        gtf.findValues("exon", "chr1_test", "-", "output_test_find");
    }

    @Test
    void findRegion() throws IOException {

        GTFFunctions gtf = new GTFFunctions(inputFile);

        gtf.findRegion("chr1_test", "1", "500", "output_test_region");
    }
}