package server;

import utils.Utils;
import worker.ServerWorker;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Properties;

/**
 * Servidor UDP que funciona como un DNS simplificado.
 *
 * <p>Recibe claves por UDP, las busca en un archivo de propiedades
 * y devuelve el valor asociado.</p>
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
     * Inicia el servidor.
     *
     * <p>Carga las propiedades DNS, abre el socket UDP y atiende
     * peticiones de forma indefinida.</p>
     */
    public static void main(String[] args) {
        Properties dnsProps = Utils.load("/dns.properties");

        try (DatagramSocket socket = new DatagramSocket(PORT)) { // El proceso queda escuchando datagramas UDP en ese puerto

            while (true) {
                DatagramPacket request = receivePacket(socket); // Recibe un datagrama cargado
                Thread t = new Thread(new ServerWorker(dnsProps, socket, request));
                t.start();
            }

        } catch (SocketException e) {
            System.err.println("No se pudo abrir el puerto " + PORT + ": " + e.getMessage());
        }
    }

    /**
     * Recibe un datagrama UDP del socket indicado.
     *
     * @param socket socket UDP del servidor.
     * @return datagrama recibido.
     */
    private static DatagramPacket receivePacket(DatagramSocket socket) {
        try {
            byte[] buffer = new byte[BUFFER_SIZE];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length); // Crea un datagrama vacío
            socket.receive(packet); // Se queda bloqueado hasta que llega un datagrama
            return packet;
        } catch (IOException e) {
            throw new RuntimeException("Error recibiendo datagrama: " + e.getMessage(), e);
        }
    }

}
