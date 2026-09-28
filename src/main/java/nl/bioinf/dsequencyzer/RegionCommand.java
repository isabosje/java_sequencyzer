package nl.bioinf.dsequencyzer;

import java.io.IOException;

public class RegionCommand {

    public static void run(String input_file, String chromosome, String start, String end) {

        try {
            GTFFunctions.findRegion(input_file, chromosome, start, end);
        } catch (IOException e) {
            System.out.println("Error reading GTF file.");
        }
    }
}
