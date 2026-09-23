import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// *
// Functie die BLAST-bestanden inleest.
// Input type: BLAST
// Date: 23/09/2026
// *

public class ReadBLAST {

    public static void readBlast(String filePath) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();

    }
}
