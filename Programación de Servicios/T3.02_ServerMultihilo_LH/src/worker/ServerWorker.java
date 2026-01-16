package worker;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Procesa una petición UDP del servidor DNS simplificado.
 *
 * <p>Recibe un datagrama con una clave, la busca en un conjunto de propiedades
 * y envía la respuesta al cliente.</p>
 */
public class ServerWorker implements Runnable{

    private final Properties dnsProps;
    private final DatagramSocket socket;
    private final DatagramPacket request;

    /**
     * Crea un worker para atender una petición concreta.
     *
     * @param dnsProps propiedades con las entradas DNS.
     * @param socket   socket UDP del servidor.
     * @param request  datagrama recibido del cliente.
     */
    public ServerWorker(Properties dnsProps, DatagramSocket socket, DatagramPacket request) {
        this.dnsProps = dnsProps;
        this.socket = socket;
        this.request = request;
    }

    /**
     * Ejecuta el procesamiento de la petición:
     * obtiene la clave, la resuelve y envía la respuesta al cliente.
     */
    @Override
    public void run() {
        try {
            String key = new String(request.getData()).trim(); // Convierte a texto los datagramas
            String replyText = dnsProps.containsKey(key) ? dnsProps.getProperty(key) : "Clave DNS no encontrada";
            byte[] replyBytes = replyText.getBytes(StandardCharsets.UTF_8);

            DatagramPacket replyPacket = new DatagramPacket(
                    replyBytes,
                    replyBytes.length,
                    request.getAddress(),
                    request.getPort()
            ); // Crea un datagrama con la respuesta, ip del cliente y puerto
            socket.send(replyPacket);

        } catch (IOException e) {
            System.err.println("Error de E/S en el servidor: " + e.getMessage());
        }

    }
}
