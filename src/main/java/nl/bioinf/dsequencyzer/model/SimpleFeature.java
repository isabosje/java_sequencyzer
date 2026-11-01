
package nl.bioinf.dsequencyzer.model;

/**
 * Represents simple GTF features such as EXON and CDS.
 */
class SimpleFeature extends Feature {

    public SimpleFeature(
            FeatureType featureType,
            String chromosome,
            int start,
            int end) {

        super(
                featureType,
                chromosome,
                start,
                end
        );
    }
}
