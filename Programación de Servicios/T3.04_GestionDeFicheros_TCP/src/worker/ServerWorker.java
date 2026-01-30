package worker;

import util.FileUtil;
import util.Protocol;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Server worker thread that handles a single client connection.
 * Receives a file path request, validates it, and sends the file content if
 * available.
 */
public class ServerWorker implements Runnable {

    private final Socket clientSocket;

    private final String LIST = "list";
    private final String SHOW = "show";
    private final String DELETE = "delete";
    private final String QUIT = "quit";

    /**
     * Creates a new server worker for handling a client connection.
     *
     * @param clientSocket the socket connected to the client
     */
    public ServerWorker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (Socket socket = clientSocket;
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream()) {

            // Read the requested file path from the client
            String command = readLine(in);

            if (command.isEmpty()) {
                System.out.println("Empty command");
                Protocol.writeStatus(out, Protocol.STATUS_KO);
                return;
            }

            // Normalize the path to prevent directory traversal attacks
            Path path = Paths.get(command).normalize();

            // Verify the file exists and is a regular file
            if (!Files.exists(path) || !Files.isRegularFile(path)) {
                Protocol.writeStatus(out, Protocol.STATUS_KO);
                return;
            }

            // Send OK status to indicate file is available
            Protocol.writeStatus(out, Protocol.STATUS_OK);

            // Transfer the file content to the client
            try (FileInputStream fileIn = new FileInputStream(path.toFile())) {
                FileUtil.transfer(fileIn, out);
            }

            System.out.println("Archivo enviado: " + path);

        } catch (IOException e) {
            System.out.println("Error atendiendo cliente: " + e.getMessage());
        }
    }

    /**
     * Reads a line from the input stream without using BufferedReader.
     * Reads byte-by-byte until a newline character is found.
     * Handles both \n (Unix) and \r\n (Windows) line terminators.
     *
     * @param in the input stream to read from
     * @return the line content (trimmed), excluding the newline character
     * @throws IOException if an I/O error occurs
     */
    private String readLine(InputStream in) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int b;

        while ((b = in.read()) != -1) {
            if (b == '\n') {
                break;
            }
            if (b != '\r') {
                buffer.write(b);
            }
        }

        return buffer.toString().trim();
    }
}
