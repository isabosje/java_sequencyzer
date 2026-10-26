package nl.bioinf.dsequencyzer.model;

/**
 * Represents EXON, CDS, etc.
 * (required becaues we cannot make a direct Feature object)
 */
class SimpleFeature extends Feature {

    public SimpleFeature(
            FeatureType type,
            String chromosome,
            int start,
            int end) {

        super(type,
                chromosome,
                start,
                end);
    }
}
