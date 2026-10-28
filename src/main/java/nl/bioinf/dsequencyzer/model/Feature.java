package nl.bioinf.dsequencyzer.model;

/**
 * Common properties shared by all GTF features.
 */
abstract class Feature {

    private final FeatureType type;
    private final String chromosome;
    private final int start;
    private final int end;

    // Constructor
    public Feature(
            FeatureType type,
            String chromosome,
            int start,
            int end) {

        this.type = type;
        this.chromosome = chromosome;
        this.start = start;
        this.end = end;
    }

    public FeatureType getType() {
        return type;
    }

    public String getChromosome() {
        return chromosome;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
