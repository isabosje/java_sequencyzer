
package nl.bioinf.dsequencyzer.command;

import nl.bioinf.dsequencyzer.GTFFunctions;
import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(
        name = "gene",
        // The @|bold is used to make --help more plain
        description = "@|bold Displays information about a specific gene.|@"
)
public class GeneCommand implements Runnable {

    // These ArgGroups change --help by sorting them
    @CommandLine.ArgGroup(
            validate = false,
            heading = "%n@|bold,blue Input:|@%n",
            order = 1
    )
    InputCommand input = new InputCommand();

    @CommandLine.ArgGroup(
            validate = false,
            heading = "%n@|bold,blue Output:|@%n",
            order = 2
    )
    OutputCommand output = new OutputCommand();

    @CommandLine.ArgGroup(
            validate = false,
            heading = "%n@|bold,blue Search:|@%n",
            order = 3
    )
    SearchGroup search = new SearchGroup();

    static class SearchGroup {

        @CommandLine.Option(
                names = {"-g", "--gene-id"},
                description = "Gene ID to search for"
        )
        String geneId = "";
    }

    @Override
    public void run() {
        try {
            GTFFunctions gtf = new GTFFunctions(input.inputFile);

            gtf.findGene(
                    search.geneId,
                    output.outputMode,
                    output.outputFile
            );

        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}

