package nl.bioinf.dsequencyzer;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "summary", description = "Shows a summary of all GTF features.")
public class SummaryCommand implements Runnable {

    @Parameters(index = "0", description = "Path to the GTF file")
    String input_file;

    @Override
