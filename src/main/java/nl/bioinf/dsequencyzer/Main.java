package nl.bioinf.dsequencyzer;

import nl.bioinf.dsequencyzer.command.FindCommand;
import nl.bioinf.dsequencyzer.command.RegionCommand;
import nl.bioinf.dsequencyzer.command.SummaryCommand;
import picocli.CommandLine.Command;
import picocli.CommandLine;

// TODO: Check if everything is OO now.

@Command(
        name = "DSequencyzer",
        header = "@|bold,underline,blue DSequencyzer|@",
        description = "@|italic,blue A bioinformatics tool to help you analyse sequences and GTF (Gene Transfer Format) files. |@",
        version = "1.0",
        mixinStandardHelpOptions = true,
        commandListHeading = "%n@|bold,blue Commands:|@%n",
        optionListHeading = "%n@|bold,blue Options:|@%n",
        subcommands = {
                RegionCommand.class,
                FindCommand.class,
                SummaryCommand.class
        })

public class Main implements Runnable {

    public static void main(String[] args) {

        CommandLine commandLine = new CommandLine(new Main());

        commandLine.setColorScheme(
                new CommandLine.Help.ColorScheme.Builder()
                        .ansi(CommandLine.Help.Ansi.ON)
                        .build()
        );

        int exitCode = commandLine.execute(args);

        System.exit(exitCode);
    }

    @Override 
    public void run() {

    }
}


