package server;

import utils.Utils;

import java.io.IOException;
import java.net.*;
import java.util.Properties;

public class Server {

    public static void main(String[] args) {

        Properties dnsProps = Utils.load("/dns.properties");

        while (true) {
            try (DatagramSocket socket = new DatagramSocket(2222)) {
                byte[] bufer = new byte[1024];
                DatagramPacket datagramaRecibido = new DatagramPacket(bufer, bufer.length);
                socket.receive(datagramaRecibido);
                String mensajeRecibido = new String(datagramaRecibido.getData()).trim();
                if(dnsProps.containsKey(mensajeRecibido)) {
                    String respuesta = dnsProps.getProperty(mensajeRecibido);
                    byte[] datos = respuesta.getBytes();

                    DatagramPacket datagramaEnviado = new DatagramPacket(
                            datos,
                            datos.length,
                            datagramaRecibido.getAddress(), // IP del cliente
                            datagramaRecibido.getPort()     // Puerto del cliente
                    );
                    socket.send(datagramaEnviado);
                }
                else {
                    String error = "Clave DNS no encontrada";
                    byte[] datos = error.getBytes();

                    DatagramPacket datagramaError = new DatagramPacket(
                            datos,
                            datos.length,
                            datagramaRecibido.getAddress(),
                            datagramaRecibido.getPort()
                    );

                    socket.send(datagramaError);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
