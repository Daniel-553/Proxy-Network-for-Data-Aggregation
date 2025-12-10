package proxy;

import TCP.TCPClientWrapper;
import UDP.UDPClientWrapper;

public class NodeLink {

    public final String address;
    public final int port;
    public Protocol protocol;

    public NodeLink(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void detectProtocol() {

        try {
            String r = TCPClientWrapper.send(address, port, "GET NAMES");
            if (r.startsWith("OK")) {
                protocol = Protocol.TCP;
                return;
            }
        } catch (Exception ignored) {}

        try {
            String r = UDPClientWrapper.send(address, port, "GET NAMES");
            if (r.startsWith("OK")) {
                protocol = Protocol.UDP;
                return;
            }
        } catch (Exception ignored) {}

        System.err.println("Cannot detect protocol for " + address + ":" + port);
    }

    public void discoverKeys(RoutingTable rt) {
        if (protocol == null) return;

        String resp = send("GET NAMES");
        if (resp == null || !resp.startsWith("OK")) return;

        String[] parts = resp.split(" ");
        for (int i = 2; i < parts.length; i++) {
            rt.registerKey(parts[i], this);
        }
    }

    public String send(String cmd) {
        if (protocol == Protocol.TCP)
            return TCPClientWrapper.send(address, port, cmd);

        return UDPClientWrapper.send(address, port, cmd);
    }
}

