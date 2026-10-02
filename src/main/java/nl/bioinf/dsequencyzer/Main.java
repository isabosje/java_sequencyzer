package nl.bioinf.dsequencyzer;

import nl.bioinf.dsequencyzer.command.*;
import picocli.CommandLine;

// TODO: Check if everything is OO now.

@CommandLine.Command(
        name = "DSequencyzer",
        version = "1.0",
        mixinStandardHelpOptions = true,
        subcommands = {
                RegionCommand.class,
                FindCommand.class,
                HeadCommand.class,
                SummaryCommand.class,
                BlastCommand.class
        })
public class Main implements Runnable {

    public static void main(String[] args) {

        new CommandLine(new Main()).execute(args);
    }

    @Override
    public void run() {

    }
}


