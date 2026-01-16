package worker;

import java.io.*;
import java.net.Socket;

public class ServerWorker implements Runnable{

    private final Socket clientSocket;

    public ServerWorker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }


    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true)
        ) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
