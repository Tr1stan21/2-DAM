package ej2;

import java.io.IOException;

public class GeneraArchivo {

    public static void createTextFile() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder( "echo OperaciónExitosa.txt");
        Process p1 = pb.start();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        createTextFile();
    }
}