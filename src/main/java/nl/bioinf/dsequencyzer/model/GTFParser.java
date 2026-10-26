package nl.bioinf.dsequencyzer.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GTFParser {

    private final List<String[]> gtfLines;

    public GTFParser(List<String[]> gtfLines) {
        this.gtfLines = gtfLines;
    }

    public static void main(String[] args) {

        Map<String, Gene> geneMap =
                new HashMap<>();

        Map<String, Transcript> transcriptMap =
                new HashMap<>();

        // Parse every line in the GTF
        for (String[] columns : gtfLines) {

            // TODO: Extract values from the line and store/cast when needed
            String type = columns[2].toLowerCase();
            String chromosome = columns[0];
            int start = Integer.parseInt(columns[3]);
            int end = Integer.parseInt(columns[4]);
            String attributes = columns[8];

            if (type == FeatureType.GENE) {

                Gene gene = new Gene(
                        chromosome,
                        start,
                        end);

                geneMap.put(geneId, gene);
            }

            else if (type == FeatureType.TRANSCRIPT) {

                // Transcript feature, always a child of a Gene
                Transcript transcript =
                        new Transcript(
                                chromosome,
                                start,
                                end);

                transcriptMap.put(
                        transcriptId,
                        transcript);

                // Couple transcript to parent-gene
                geneMap.get(geneId)
                        .addTranscript(
                                transcript);
            }

            else {
                // Generic feature, always a child of a Transcript
                Feature feature =
                        new SimpleFeature(
                                type,
                                chromosome,
                                start,
                                end);

                // Couple feature to parent-transcript
                transcriptMap.get(transcriptId)
                        .addFeature(
                                feature);
            }
        }
    }
}
