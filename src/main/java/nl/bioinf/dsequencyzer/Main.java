package nl.bioinf.dsequencyzer;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
        name = "DSequencyzer", version="1.0", mixinStandardHelpOptions = true)
public class Main implements Runnable {

    @Option(
            names = {"-s", "--summary"},
            description = "Shows a summary of all GTF features."
    )
    boolean summary;

    @Option(
            names = {"-f", "--find"},
            description = "Finds a gene, chromosome or feature in a GTF file."
    )
    boolean find;

    @Option(
            names = {"-r", "--region"},
            description = "Finds features within a genomic region."
    )
    boolean region;

    @Option(
            names = {"-h", "--head"},
            description = "Shows the first 10 lines of a file."
    )
    boolean head;

    @Option(
            names = {"-l", "--length"},
            description = "Returns the length of a nucleotide sequence."
    )
    boolean length;

    @Option(
            names = {"-g", "--gc"},
            description = "Returns the GC percentage of a nucleotide sequence."
    )
    boolean gc;

    public static void main(String[] args) {
        new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {

    }
}

