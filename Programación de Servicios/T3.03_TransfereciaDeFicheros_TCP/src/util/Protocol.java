package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Protocol utility class for TCP file transfer communication.
 * Handles status messages (OK/KO) between client and server.
 */
public class Protocol {

    /** Platform-specific line separator for status messages. */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /** Status message indicating successful operation. */
    public static final String STATUS_OK = "OK" + LINE_SEPARATOR;
    /** Status message indicating failed operation. */
    public static final String STATUS_KO = "KO" + LINE_SEPARATOR;

    /**
     * Writes a status message to the output stream.
     *
     * @param out    the output stream to write to
     * @param status the status message to send
     * @throws IOException if an I/O error occurs
     */
    public static void writeStatus(OutputStream out, String status) throws IOException {
        out.write(status.getBytes());
        out.flush();
    }

    /**
     * Reads a status message from the input stream.
     * Reads bytes until a newline character is found, without consuming extra data.
     * Supports both \n (Unix/Linux) and \r\n (Windows) line terminators.
     *
     * @param in the input stream to read from
     * @return the status message (trimmed), or null if end of stream reached
     * @throws IOException if an I/O error occurs
     */
    public static String readStatus(InputStream in) throws IOException {
        byte[] buffer = new byte[4];
        int bytesRead = 0;
        int b;

        while (bytesRead < buffer.length && (b = in.read()) != -1) {
            buffer[bytesRead++] = (byte) b;
            if (b == '\n') {
                break;
            }
        }

        if (bytesRead == 0) {
            return null;
        }

        return new String(buffer, 0, bytesRead).trim();
    }
}
