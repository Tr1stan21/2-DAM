package client;

import util.FileUtil;
import util.Protocol;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * TCP file transfer client application.
 * Connects to a remote server to request and download files.
 * The client sends the file path and receives the file content if available.
 */
public class MainFileClientApp {

    private static final int DEFAULT_PORT = 4321;

    public static void main(String[] args) {

        String host = "127.0.0.1";
        int port = DEFAULT_PORT;

        if (args != null && args.length >= 2) {
            host = args[0].trim();
            port = parsePortOrDefault(args[1]);
        }

        try (Socket socket = new Socket(host, port);
                Scanner scanner = new Scanner(System.in);
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream()) {

            System.out.println("Inserte la ruta del fichero: ");
            String path = scanner.nextLine();

            // Send file path to server
            out.write((path).getBytes());
            out.flush();    

            // Read server response status (OK/KO)
            String status = Protocol.readStatus(in);

            if (status == null) {
                System.out.println("Status no recibido");
                return;
            }

            if ("KO".equals(status)) {
                System.out.println("Servidor: KO (archivo no encontrado).");
                return;
            }

            if (!"OK".equals(status)) {
                throw new IOException("Respuesta inválida: " + status);
            }

            // Receive and save the file locally
            String fileName = Path.of(path).getFileName().toString();

            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                FileUtil.transfer(in, fos);
            }

            System.out.println("Archivo recibido: " + fileName);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Parses the port number from a string, or returns the default port if invalid.
     * Valid port range is 1 to 65535.
     *
     * @param rawPort the port string to parse
     * @return the parsed port number, or DEFAULT_PORT if parsing fails
     */
    private static int parsePortOrDefault(String rawPort) {
        try {
            int port = Integer.parseInt(rawPort.trim());
            return (port >= 1 && port <= 65535) ? port : DEFAULT_PORT;
        } catch (Exception e) {
            return DEFAULT_PORT;
        }
    }
}
