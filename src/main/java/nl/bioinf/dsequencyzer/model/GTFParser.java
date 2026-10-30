
package nl.bioinf.dsequencyzer.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GTFParser {

    private final List<String[]> gtfLines;

    public GTFParser(List<String[]> gtfLines) {
        this.gtfLines = gtfLines;
    }

    public Map<String, Gene> parse() {
        Map<String, Gene> geneMap = new HashMap<>();
        Map<String, Transcript> transcriptMap = new HashMap<>();

        for (String[] columns : gtfLines) {
            String chromosome = columns[0];
            String type = columns[2];
            int start = Integer.parseInt(columns[3]);
            int end = Integer.parseInt(columns[4]);
            String attributes = columns[8];

            String geneId = getId(attributes, "gene_id");
            String transcriptId = getId(attributes, "transcript_id");

            if ("gene".equals(type)) {
                geneMap.put(geneId, new Gene(chromosome, start, end));

            } else if ("transcript".equals(type)) {
                Transcript transcript =
                        new Transcript(chromosome, start, end);

                transcriptMap.put(transcriptId, transcript);
                geneMap.get(geneId).addTranscript(transcript);

            } else if ("exon".equals(type) || "CDS".equals(type)) {
                Transcript transcript = transcriptMap.get(transcriptId);

                if (transcript != null) {
                    FeatureType featureType = "CDS".equals(type)
                            ? FeatureType.CDS : FeatureType.EXON;

                    transcript.addFeature(new SimpleFeature(
                            featureType, chromosome, start, end));
                }
            }
        }

        return geneMap;
    }

    private String getId(String attributes, String key) {
        for (String attribute : attributes.split(";")) {
            String[] parts = attribute.trim().split("\\s+", 2);

            if (parts.length == 2 && parts[0].equals(key)) {
                return parts[1].replace("\"", "").trim();
            }
        }

        return null;
    }
}
