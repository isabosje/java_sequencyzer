
package nl.bioinf.dsequencyzer.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
                    throw new IllegalArgumentException("INVALID: Empty FASTA header.");
                }
                headerFound = true;
            } else {
                if (!headerFound) {
                    throw new IllegalArgumentException("DNA sequence found before FASTA header.");
                }
                sequence.append(line);
            }

        }

        if (sequence.isEmpty() || !headerFound) {
            throw new IllegalArgumentException("Empty or invalid FASTA file.");
        }
        return sequence.toString();
    }
    public static List<String> readFirstLines(
            String input, int numberOfLines)
        throws IOException {
        if (numberOfLines <= 0) {
            throw new IllegalArgumentException("Number of lines must be greater than 0 and not negative.");
        }
        try (var lines = Files.lines(Path.of(input))) {
            return lines.limit(numberOfLines).toList();
        }
    }
}
