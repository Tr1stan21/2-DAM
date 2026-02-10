package worker;

import util.CommandProcessor;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
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
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {

            String line = in.readLine();
            line = line.trim();
            if (line.isEmpty()) {
                sendLine(out, "KO");
                return;
            }

            String[] rawCommand = line.split(" ",2);
            String command = rawCommand[0];

            Path path;
            CommandProcessor cp;
            String[] response;
            switch (command) {
                case LIST:
                    path = Paths.get(rawCommand[1]).normalize();
                    cp = new CommandProcessor(path.toString());
                    response = cp.getCommandMap().get(LIST);
                    if(response.length == 1) {
                        sendLine(out, response[0]);
                    }
                    else {
                        sendLine(out, response[0]);
                        sendLine(out, response[1]);
                    }
                    break;

                case SHOW:
                    path = Paths.get(rawCommand[1]).normalize();
                    cp = new CommandProcessor(path.toString());
                    response = cp.getCommandMap().get(SHOW);

                    if (response.length == 1) {
                        sendLine(out, response[0]);
                    } else {
                        sendLine(out, response[0]);
                        sendLine(out, response[1]);
                    }
                    break;

                case DELETE:
                    path = Paths.get(rawCommand[1]).normalize();
                    cp = new CommandProcessor(path.toString());
                    response = cp.getCommandMap().get(DELETE);

                    sendLine(out, response[0]);
                    break;

                case QUIT:
                    cp = new CommandProcessor();
                    response = cp.getCommandMap().get(QUIT);
                    sendLine(out, response[0]);
                    break;

                default:
                    sendLine(out, "KO");
                    break;
            }


        } catch (IOException e) {
            System.out.println("Error atendiendo cliente: " + e.getMessage());
        }
    }


    private static void sendLine(BufferedWriter out, String s) throws IOException {
        out.write(s);
        out.newLine();
        out.flush();
    }

}
