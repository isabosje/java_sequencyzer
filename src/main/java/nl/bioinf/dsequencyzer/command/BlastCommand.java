package nl.bioinf.dsequencyzer.command;

import nl.bioinf.dsequencyzer.io.ReadBLAST;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(
        name = "blast",
        description = "Processes a BLAST result file."
)
public class BlastCommand implements Runnable {

    @Parameters(index = "0", description = "BLAST file")
    String file;

    @Parameters(index = "1", description = "BLAST option: hits, average or best")
    String option;

    @Override
    public void run() {

        try {
            ReadBLAST blastReader = new ReadBLAST();

            String result = blastReader.readBlast(file, option);

            System.out.println(option + ": " + result);

        } catch (Exception e) {
            System.out.println("Error reading BLAST file: " + e.getMessage());
         }

    }

//     test, nog verwijderen
//    public static void main(String[] args) {
//        new CommandLine(new BlastCommand()).execute(args);
//    }
}