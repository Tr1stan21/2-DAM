package client;

import java.io.*;

import java.net.Socket;
import java.nio.file.Path;
import java.util.Scanner;

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
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream())) {

            System.out.println("Inserte la ruta del fichero: ");
            String path = scanner.nextLine();

            out.writeUTF((path + System.lineSeparator()));
            out.flush();

            String status = in.readUTF();

            if ("KO".equals(status)) {
                System.out.println("Servidor: KO (archivo no encontrado).");
                return;
            }
            if (!"OK".equals(status)) {
                throw new IOException("Respuesta inválida: " + status);
            }

            String fileName = Path.of(path).getFileName().toString();

            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                byte[] buffer = new byte[8192];
                long remaining = in.readLong();

                while (remaining > 0) {
                    int read = in.read(buffer, 0, (int) Math.min(buffer.length, remaining));
                    if (read == -1) break;

                    fos.write(buffer, 0, read);
                    remaining -= read;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int parsePortOrDefault(String rawPort) {
        try {
            int port = Integer.parseInt(rawPort.trim());
            return (port >= 1 && port <= 65535) ? port : DEFAULT_PORT;
        } catch (Exception e) {
            return DEFAULT_PORT;
        }
    }

}
