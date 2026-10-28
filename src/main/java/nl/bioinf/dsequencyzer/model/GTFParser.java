package nl.bioinf.dsequencyzer.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GTFParser {

    private final List<String[]> gtfLines;

    public GTFParser(List<String[]> gtfLines) {
        this.gtfLines = gtfLines;
    }

        // Parse every line in the GTF
        for (String[] columns : gtfLines) {

            // TODO: Extract values from the line and store/cast when needed
            String chromosome = columns[0];
            int start = Integer.parseInt(columns[3]);
            int end = Integer.parseInt(columns[4]);
            String attributes = columns[8];


                    Gene gene = new Gene(
                            chromosome,
                            start,
                            end);

                    geneMap.put(geneId, gene);
                }

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
                    }
                }
        }
    }
