package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Client {

    private static final int SERVER_PORT = 2222;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
             DatagramSocket clientSocket = new DatagramSocket()) {

            InetAddress serverIp = InetAddress.getLocalHost();
            String consult;

            do {
                System.out.print("Introduce nombre del host a consultar: ");
                consult = in.readLine();

                byte[] toServer = consult.getBytes(StandardCharsets.UTF_8);
                System.out.println("Enviando " + toServer.length + " bytes al servidor.");

                DatagramPacket request = new DatagramPacket(toServer, toServer.length, serverIp, SERVER_PORT);
                clientSocket.send(request);

                byte[] buffer = new byte[BUFFER_SIZE];
                DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
                clientSocket.receive(responsePacket);

                String answer = new String(
                        responsePacket.getData()).trim();

                System.out.println("Respuesta del servidor: " + answer);

            } while (!consult.isEmpty());

        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
    }
}

