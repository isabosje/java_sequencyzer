
package nl.bioinf.dsequencyzer.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFasta {

    public static String readSequence(String input) throws IOException {
        StringBuilder sequence = new StringBuilder();
        boolean headerFound = false;

        for (String line : Files.readAllLines(Path.of(input))) {
            line = line.trim();

            if (line.isEmpty()) {
                continue;
            }

            if (line.startsWith(">")) {
                if (line.length() == 1) {
                    throw new IllegalArgumentException("Empty FASTA header.");
                }
                headerFound = true;
            } else {
                if (!headerFound || !line.matches("[ACGTUacgtu]+")) {
                    throw new IllegalArgumentException("Invalid FASTA file.");
                }
                sequence.append(line);
            }
        }

        if (sequence.isEmpty() || !headerFound) {
            throw new IllegalArgumentException("Empty or invalid FASTA file.");
        }

        return sequence.toString();
    }
}
