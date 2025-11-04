package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReader {

    private static final File DATOS_CSV = new File("src/main/resources/datos.csv");

    /**
     * Reads a CSV file and returns its content in a list of string arrays
     *
     * @return
     */
    public List<String[]> getLines() {
        List<String[]> dataLines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(DATOS_CSV))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String [] data = line.split(";");
                dataLines.add(data);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dataLines;
    }
}