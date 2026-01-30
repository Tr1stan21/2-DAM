package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utility class for file I/O operations.
 * Provides efficient stream-to-stream data transfer with buffering.
 */
public class FileUtil {

    /** Buffer size for stream operations (8 KB). */
    private static final int BUFFER_SIZE = 8192;

    /**
     * Transfers all data from an input stream to an output stream using buffered
     * I/O.
     * The transfer continues until the end of the input stream is reached.
     *
     * @param in  the input stream to read from
     * @param out the output stream to write to
     * @throws IOException if an I/O error occurs during transfer
     */
    public static void transfer(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        out.flush();
    }
}
