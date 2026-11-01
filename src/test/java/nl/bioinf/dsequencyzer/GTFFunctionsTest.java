package nl.bioinf.dsequencyzer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

class GTFFunctionsTest {
    private String inputFile;

    @BeforeEach
    void setUp() {
        inputFile = getClass()
                .getClassLoader()
                .getResource("generated_test.gtf")
                .getPath();
    }
}