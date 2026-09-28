import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// *
// Description: Leest BLAST-bestanden in en verwerkt de resultaten
// Input type: BLAST
// Date: 27/09/2026
// v0.2
// *
public class ReadBLAST {

    public String readBlast(String filePath, String option) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        // Resultaten bijhouden
        int hits = 0;
        double totalIdentity = 0;
        double bestIdentity = 0;

        String line;

        // BLAST-regels (columns) verwerken
        while ((line = reader.readLine()) != null) {

            if (line.startsWith("#") || line.isEmpty()) {
                continue;
            }

            String[] columns = line.split("\t");

            hits++;

            double identity = Double.parseDouble(columns[2]);
            totalIdentity += identity;

            if (identity > bestIdentity) {
                bestIdentity = identity;
            }
        }

        reader.close();

        // informatie teruggeven
        if (option.equals("hits")) {  // Aantal blast hits
            return String.valueOf(hits);
        }
        else if (option.equals("average")) { //
            return String.valueOf(totalIdentity / hits);
        }
        else if (option.equals("best")) {
            return String.valueOf(bestIdentity);
        }

        return "Unknown option";
    }
}