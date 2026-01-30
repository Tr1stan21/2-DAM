package server;

import worker.ServerWorker;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TCP file transfer server application.
 * Listens for client connections and serves files from the server.
 * Each client request is handled by a separate worker thread.
 */
public class MainFileServerApp {

    private static final int DEFAULT_PORT = 4321;

    public static void main(String[] args) {

        int port = parsePortOrDefault(args);

        // Thread pool to handle multiple concurrent client connections
        ExecutorService pool = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor escuchando en puerto " + port);

            // Accept incoming client connections and process them in separate threads
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

    /**
     * Parses the port number from command-line arguments, or returns the default
     * port.
     * Valid port range is 1 to 65535.
     *
     * @param args command-line arguments containing the port number
     * @return the parsed port number, or DEFAULT_PORT if invalid
     */
    private static int parsePortOrDefault(String[] args) {
        if (args.length != 1)
            return DEFAULT_PORT;

        try {
            int port = Integer.parseInt(args[0].trim());
            return (port >= 1 && port <= 65535) ? port : DEFAULT_PORT;
        } catch (NumberFormatException e) {
            return DEFAULT_PORT;
        }
    }
}
