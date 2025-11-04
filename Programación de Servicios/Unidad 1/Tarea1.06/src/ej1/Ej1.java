package ej1;

import java.io.IOException;

public class Ej1 {

    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder pb1 = new ProcessBuilder(("xcopy C:\\Windows\\explorer.exe src\\resources").split(" "));

        Process p1 = pb1.start();
        System.out.println(p1.waitFor());
    }

}
