package client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class MainCommandClientApp {

    private static final int DEFAULT_PORT = 2121;

    public static void main(String[] args) {

        String host = "127.0.0.1";
        int port = DEFAULT_PORT;

        if (args != null && args.length >= 2) {
            host = args[0].trim();
            port = parsePortOrDefault(args[1]);
        }

        try (Socket socket = new Socket(host, port);
             Scanner scanner = new Scanner(System.in);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {

            while (true) {

                System.out.print("> ");
                String cmd = scanner.nextLine();

                out.write(cmd);
                out.newLine();
                out.flush();

                String status = in.readLine();
                System.out.println(status);

                if ("OK".equals(status)) {
                    String s;
                    while ((s = in.readLine()) != null) {
                        if (s.isEmpty()) break;   // fin de list
                        System.out.println(s);
                    }
                }

                if ("quit".equalsIgnoreCase(cmd.trim())) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
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
