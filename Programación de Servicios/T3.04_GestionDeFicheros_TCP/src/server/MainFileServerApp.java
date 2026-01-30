package server;

import util.Utils;
import worker.ServerWorker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TCP file transfer server application.
 * Listens for client connections and serves files from the server.
 * Each client request is handled by a separate worker thread.
 */
public class MainFileServerApp {

    private static final int DEFAULT_PORT = 2121;

    public static void main(String[] args) {

        int port = readPort();

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


    private static int readPort() {
        Properties serverProps = Utils.load("/server.properties");
        if (serverProps.containsKey("puerto")) {
            try {
                int port = Integer.parseInt(serverProps.getProperty("puerto"));
                return (port >= 1 && port <= 65535) ? port : DEFAULT_PORT;
            }catch (NumberFormatException e) {
                return DEFAULT_PORT;
            }
        }
        return DEFAULT_PORT;
    }
}
