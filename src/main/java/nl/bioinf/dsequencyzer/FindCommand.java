package nl.bioinf.dsequencyzer;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.IOException;

@Command(name = "find", description = "Finds features by using additional values.")
public class FindCommand implements Runnable {

    @Parameters(index = "0", description = "Path to the GTF file")
    String input_file;

    @Parameters(index = "1", description = "Search option: gene, chromosome or feature")
    String option;

    @Parameters(index = "2", description = "Value to search for")
    String value;

    @Override
    public void run() {
        try {
            GTFFunctions.findValue(input_file, option, value);
        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}
