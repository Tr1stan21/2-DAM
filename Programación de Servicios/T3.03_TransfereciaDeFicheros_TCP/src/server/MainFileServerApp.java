package server;

import worker.ServerWorker;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainFileServerApp {

    public static void main(String[] args) {

        int port;
        try{
            port = Integer.parseInt(args[0]);
            if (!(port >= 1 && port <= 65535)){
                port = 4321;
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        ExecutorService pool = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor escuchando en puerto " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                pool.execute(new ServerWorker(clientSocket));
            }

        } catch (IOException e) {
            System.out.println("Error servidor: " + e.getMessage());
        } finally {
            pool.shutdown();
        }
    }

}
