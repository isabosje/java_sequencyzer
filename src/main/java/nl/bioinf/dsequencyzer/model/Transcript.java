package nl.bioinf.dsequencyzer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Transcript data type storing standard data
 * and a list of child-features (can be anything)
 */
class Transcript extends Feature {

    private final List<Feature> features =
            new ArrayList<>();

    public Transcript(String chromosome,
                      int start,
                      int end) {

        super(
                FeatureType.TRANSCRIPT,
                chromosome,
                start,
                end);
    }

    public void addFeature(
            Feature feature) {

        features.add(feature);
    }

    public List<Feature> getFeatures() {
        return features;
    }
}