package nl.bioinf.dsequencyzer;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

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

    @Parameters(
            description = "Input file, sequence and additional arguments."
    )
    String[] arguments;

    public static void main(String[] args) {
        new CommandLine(new Main()).execute(args);
    }

    @Override
    public void run() {

        if (length) {
            LengthCommand.run(arguments[0]);
        }

        if (gc) {
            GCCommand.run(arguments[0]);
        }

        if (head) {
            HeadCommand.run(arguments[0]);
        }

        if (summary) {
            SummaryCommand.run(arguments[0]);
        }

        if (find) {
            FindCommand.run(arguments[0], arguments[1], arguments[2]);
        }

        if (region) {
            RegionCommand.run(
                    arguments[0],
                    arguments[1],
                    arguments[2],
                    arguments[3]
            );
        }
    }
}

