package nl.bioinf.dsequencyzer;

import picocli.CommandLine;
import picocli.CommandLine.Command;

// OO checken (Object Oriented)

@Command(
        subcommands = {
                RegionCommand.class,
                LengthCommand.class,
                GCCommand.class,
                HeadCommand.class,
                SummaryCommand.class,
                FindCommand.class
        },
        name = "DSequencyzer",
        version = "1.0",
        mixinStandardHelpOptions = true)

public class Main implements Runnable {

    public static void main(String[] args) {
        new CommandLine(new Main()).execute(args);
    }

    @Override
    public void run() {

    }
}

