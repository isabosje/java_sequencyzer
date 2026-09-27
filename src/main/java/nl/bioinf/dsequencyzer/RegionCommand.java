package nl.bioinf.dsequencyzer;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "region", description = "Finds features within a genomic region.")
public class RegionCommand implements Runnable {

    @Parameters(index = "0", description = "Path to the GTF file")
    String input_file;

    @Parameters(index = "1", description = "Chromosome")
    String chromosome;

    @Parameters(index = "2", description = "Start coordinate")
    String start;

    @Parameters(index = "3", description = "End coordinate")
    String end;

    @Override
    public void run() {
        try {
            GTFFunctions.findRegion(input_file, chromosome, start, end);
        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}
