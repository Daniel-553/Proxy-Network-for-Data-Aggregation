package TCP;

import java.io.*;
import java.net.*;

public class TCPClientWrapper {
    public static String send(String address, int port, String command) {
        try (Socket socket = new Socket(address, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out.println(command);

            if (command.equals("QUIT"))
                return "OK";

            return in.readLine();

        } catch (Exception e) {
            return "NA";
        }
    }
}

