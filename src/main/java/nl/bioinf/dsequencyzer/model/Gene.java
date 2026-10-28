package nl.bioinf.dsequencyzer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Gene data type storing standard data
 * and a list of child-transcripts.
 */
public class Gene extends Feature {

    private final List<Transcript> transcripts = new ArrayList<>();

    public Gene(String chromosome, int start, int end) {

        // Call the Feature constructor with the proper Gene type
        super(
                FeatureType.GENE,
                chromosome,
                start,
                end
        );
    }

    public void addTranscript(Transcript transcript) {
        transcripts.add(transcript);
    }

    public List<Transcript> getTranscripts() {
        return transcripts;
    }
}
