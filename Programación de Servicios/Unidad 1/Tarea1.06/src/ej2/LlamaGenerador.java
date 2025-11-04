package ej2;

import java.io.IOException;

public class LlamaGenerador {

    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(("java -jar out\\artifacts\\Tarea1_06_jar\\Tarea1.06.jar echo Este es el contenido > archivo.txt").split(" "));
        Process p1 = pb.start();
        p1.waitFor();
    }

}
