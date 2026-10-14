package nl.bioinf.dsequencyzer;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class GTFFunctionsTest {

    @Test
    void summaryCount() throws IOException {
        GTFFunctions gtf = new GTFFunctions("test.gtf");

        assertDoesNotThrow(() -> gtf.summaryCount());
    }

    @Test
    void findValue() throws IOException {
        GTFFunctions gtf = new GTFFunctions("test.gtf");

        assertDoesNotThrow(() -> gtf.findValues("feature", "gene"));
    }

    @Test
    void findRegion() throws IOException {
        GTFFunctions gtf = new GTFFunctions("test.gtf");

        assertDoesNotThrow(() -> gtf.findRegion("chr1", "100", "500"));
    }
}