package server;

import utils.Utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Servidor UDP que actúa como un DNS simplificado.
 *
 * <p>Escucha peticiones en un puerto UDP, busca la clave recibida
 * en un archivo de propiedades y devuelve el valor asociado.</p>
 *
 * <p>Si la clave no existe, responde con un mensaje de error.</p>
 */
public class Server {

    /**
     * Puerto en el que el servidor escucha las peticiones UDP.
     */
    private static final int PORT = 2222;

    /**
     * Tamaño máximo del buffer para recibir datagramas.
     */
    private static final int BUFFER_SIZE = 1024;

    /**
     * Punto de entrada del servidor.
     *
     * <p>Carga el archivo de propiedades DNS y atiende peticiones
     * de forma indefinida mediante sockets UDP.</p>
     */
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
