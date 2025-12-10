package UDP;

import java.net.*;

public class UDPClientWrapper {

    public static String send(String address, int port, String command) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();


            byte[] data = (command + " ").getBytes();
            DatagramPacket p = new DatagramPacket(
                    data, data.length,
                    InetAddress.getByName(address), port);
            socket.send(p);


            if (command.equals("QUIT")) {
                socket.close();
                return "OK";
            }


            byte[] buffer = new byte[256];
            DatagramPacket resp = new DatagramPacket(buffer, buffer.length);
            socket.receive(resp);

            return new String(resp.getData(), 0, resp.getLength());

        } catch (Exception e) {
            return "NA";
        } finally {
            if (socket != null)
                socket.close();
        }
    }
}

