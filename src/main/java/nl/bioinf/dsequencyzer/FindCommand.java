package nl.bioinf.dsequencyzer;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "find", description = "Find a gene, chromosome or feature and filter using an additional value.")
public class SummaryCommand implements Runnable {

    @Parameters(index = "0", description = "Path to the GTF file")
    String input_file;

    @Parameters(index = "1", description = "Search option: gene, chromosome or feature")
    String option;

    @Parameters(index = "2", description = "Value to search for")
    String value;

    @Override
