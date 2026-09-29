package nl.bioinf.dsequencyzer;

import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(
        name = "HeadCommand",
        description = "Gives 10 lines")

public class HeadCommand implements Runnable {
    @CommandLine.Option(names = {"-i", "--input"},
            description = "Input file")
    String input_file;

    @Override
    public void run() {

        try {
            FileFunctions file = new FileFunctions(input_file);

            file.getHeader();

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }
}
