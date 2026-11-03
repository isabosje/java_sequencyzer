package nl.bioinf.dsequencyzer.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;

import picocli.CommandLine.Option;

public class VerboseCommand {

    @Option(
            names = {"-v", "--verbose"},
            description = "Increase verbosity."
    )
    private boolean[] verbose = new boolean[0];

    private static final Logger logger =
            LogManager.getLogger(VerboseCommand.class);

    public void run() {
        // Change logging level depending on the amount of 'v's
        if (verbose.length > 1) {
            Configurator.setRootLevel(Level.INFO);
            logger.info("Verbosity level set to INFO");
        } else if (verbose.length > 0) {
            Configurator.setRootLevel(Level.WARN);
            logger.warn("Verbosity level set to WARN");
        }
    }
}