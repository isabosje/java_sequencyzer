package nl.bioinf.dsequencyzer;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "head", description = "Shows the first 10 lines of a file.")
public class HeadCommand implements Runnable {

    @Parameters(index = "0", description = "Path to the file")
    String input_file;

    @Override
    public void run() {
        try {
            FileFunctions.getHeader(input_file);
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }
}
