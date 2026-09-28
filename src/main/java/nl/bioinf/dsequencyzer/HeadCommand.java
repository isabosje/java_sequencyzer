package nl.bioinf.dsequencyzer;

import java.io.IOException;

public class HeadCommand {

    public void run(String input_file) {

        try {
            FileFunctions file = new FileFunctions(input_file);

            file.getHeader();

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }
}
