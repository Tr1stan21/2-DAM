package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * Cliente UDP que envía consultas de nombres de host a un servidor DNS sencillo
 * y muestra la respuesta recibida.
 *
 * <p>El cliente solicita al usuario un nombre de host por consola, lo envía
 * al servidor mediante un datagrama UDP y espera la respuesta asociada.</p>
 *
 * <p>La comunicación finaliza cuando el usuario introduce una cadena vacía.</p>
 */
public class Client {

    /**
     * Puerto en el que escucha el servidor UDP.
     */
    private static final int SERVER_PORT = 2222;

    /**
     * Tamaño máximo del buffer para recibir datagramas.
     */
    private static final int BUFFER_SIZE = 1024;

    /**
     * Punto de entrada de la aplicación cliente.
     *
     * <p>Lee consultas desde la entrada estándar, las envía al servidor UDP
     * y muestra la respuesta recibida.</p>
     */
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

