package server;

import utils.Utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Server {

    private static final int PORT = 2222;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        Properties dnsProps = Utils.load("/dns.properties");

        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);

            while (true) {
                socket.receive(receivedPacket);

                String key = new String(receivedPacket.getData()).trim();

                String replyText = dnsProps.containsKey(key) ? dnsProps.getProperty(key) : "Clave DNS no encontrada";

                byte[] replyBytes = replyText.getBytes(StandardCharsets.UTF_8);

                DatagramPacket replyPacket = new DatagramPacket(
                        replyBytes,
                        replyBytes.length,
                        receivedPacket.getAddress(),
                        receivedPacket.getPort()
                );

                socket.send(replyPacket);

                receivedPacket.setLength(buffer.length);
            }

        } catch (SocketException e) {
            System.err.println("No se pudo abrir el puerto " + PORT + ": " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de E/S en el servidor: " + e.getMessage());
        }
    }
}
