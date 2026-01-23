package server;

import worker.ServerWorker;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainFileServerApp {

    private static final int DEFAULT_PORT = 4321;

    public static void main(String[] args) {

        int port = parsePortOrDefault(args);

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

    private static int parsePortOrDefault(String[] args) {
        if (args.length != 1) return DEFAULT_PORT;

        try {
            int port = Integer.parseInt(args[0].trim());
            return (port >= 1 && port <= 65535) ? port : DEFAULT_PORT;
        } catch (NumberFormatException e) {
            return DEFAULT_PORT;
        }
    }


}
