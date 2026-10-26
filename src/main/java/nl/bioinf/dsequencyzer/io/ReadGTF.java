package nl.bioinf.dsequencyzer.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadGTF {

    private String inputFile;

    public ReadGTF(String inputFile) {
        this.inputFile = inputFile;
    }

    public List<String[]> readFile() throws IOException {

        File file = new File(inputFile);

        if (!file.exists() || !file.isFile()) {
            throw new IOException("Input file does not exist.");
        }

        if (file.length() == 0) {
            throw new IOException("Input file is empty.");
        }

        List<String[]> lines = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String line = reader.readLine();
    }





