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

        // Parse every line in the GTF
        for (String[] columns : gtfLines) {

            // TODO: Extract values from the line and store/cast when needed
            String type = columns[2].trim();
            String chromosome = columns[0];
            int start = Integer.parseInt(columns[3]);
            int end = Integer.parseInt(columns[4]);
            String attributes = columns[8];

            String[] attributesArray = attributes.split(";");
            String geneId = "";
            String transcriptId = "";

            for (String attribute : attributesArray) {

                if (attribute.trim().startsWith("gene_id")) {
                    geneId = attribute.replace("gene_id", "").replace("/", "").trim();

                } else if (attribute.trim().startsWith("transcript_id")) {
                    transcriptId = attribute.replace("transcript_id", "").replace("/", "").trim();
                }
            }

            if (type.equals("gene")) {
                {

                    Gene gene = new Gene(
                            chromosome,
                            start,
                            end);

                    geneMap.put(geneId, gene);
                }
            }
            else if (type.equals("transcript")) {

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
                Gene gene = geneMap.get(geneId);

                if (gene != null) {
                    gene.addTranscript(transcript);
                    }
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
                    Transcript transcript = transcriptMap.get(transcriptId);

                    if (transcript != null) {
                        transcript.addFeature(feature);
                    }
                }
            }
            return geneMap;
        }
    }
