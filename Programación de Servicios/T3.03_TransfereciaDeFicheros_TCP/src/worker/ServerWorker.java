package worker;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerWorker implements Runnable{

    private final Socket clientSocket;

    public ServerWorker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }


    @Override
    public void run() {
        try (Socket socket = clientSocket;
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            String pathLine = in.readUTF();
            Path path = Paths.get(pathLine).normalize();

            if (pathLine.isEmpty() || !Files.exists(path)) {
                out.writeUTF(("KO" + System.lineSeparator()));
                out.flush();
                return;
            }

            out.writeUTF(("OK" + System.lineSeparator()));

            try (FileInputStream fileIn = new FileInputStream(path.toFile())) {
                byte[] buffer = new byte[8192];
                int n;
                while ((n = fileIn.read(buffer)) != -1) {
                    out.write(buffer, 0, n);
                }
            }

            out.flush();

        } catch (IOException e) {
            System.out.println("Error atendiendo cliente: " + e.getMessage());
        }

    }

}
