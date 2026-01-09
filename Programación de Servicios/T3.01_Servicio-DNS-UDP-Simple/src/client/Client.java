package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            try {
                String cadena;
                do {
                    DatagramSocket clientSocket = new DatagramSocket();

                    InetAddress IPServidor = InetAddress.getLocalHost();// localhost
                    int puerto = 2222; // puerto por el que escucha

                    System.out.print("Introduce nombre del host a consultar: ");
                    cadena = in.readLine();

                    byte[] enviados = cadena.getBytes();
                    System.out.println("Enviando " + enviados.length + " bytes al servidor.");
                    DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
                    clientSocket.send(envio);

                    byte[] recibidos = new byte[1024];
                    DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
                    clientSocket.receive(recibo);
                    String respuesta = new String(recibo.getData()).trim();

                    System.out.println("Respuesta del servidor: "+ respuesta);
                    clientSocket.close();

                } while (!cadena.isEmpty());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }
}
