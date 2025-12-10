package proxy;


import TCP.TCPServer;
import UDP.UDPServer;

import java.util.*;

public class Proxy {

    private static int listenPort;
    private static final List<NodeLink> upstreamNodes = new ArrayList<>();
    private static final RoutingTable routing = new RoutingTable();

    public static void main(String[] args) {
        parseArgs(args);

        System.out.println("Proxy starting on port " + listenPort);


       new Thread();
       new Thread();


        for (NodeLink link : upstreamNodes) {
            link.detectProtocol();
            link.discoverKeys(routing);
        }

        System.out.println("Proxy ready.");
    }

    private static void parseArgs(String[] args) {
        if (args.length < 3) {
            System.err.println("Usage: java Proxy -port <port> -server <address> <port> ...");
            System.exit(1);
        }

        for (int i=0; i<args.length; i++) {
            if (args[i].equals("-port")) {
                listenPort = Integer.parseInt(args[++i]);
            }
            else if (args[i].equals("-server")) {
                String addr = args[++i];
                int port = Integer.parseInt(args[++i]);
                upstreamNodes.add(new NodeLink(addr, port));
            }
        }
    }
}

