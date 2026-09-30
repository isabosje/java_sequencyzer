package nl.bioinf.dsequencyzer;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.ArgGroup;
import java.io.IOException;

@Command(
        name = "summary",
        description = "@|bold Gives a summary containing features depending on file type.|@")

public class SummaryCommand implements Runnable {

    @ArgGroup(
            validate = false,
            heading = "%n@|bold,blue Input:|@%n",
            order = 1
    )
    InputGroup Input;

    static class InputGroup {

        @Option(
                names = {"-i, --input"},
                description = "@|bold Input a valid GTF or FASTA file.|@",
                required = true)
        String input_file;
    }

    @Override
    public void run() {

        // Needs changes for different filetypes
        try {
            GTFFunctions gtf = new GTFFunctions(Input.input_file);
            gtf.summaryCount();

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}


