package socket.udp.relay;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server
{
    final static int MAX_BUFFER_SIZE = 100;

    public static void main(String[] args) throws IOException
    {
        if (args.length != 1)
        {
            System.out.println("usage: java socket.udp.relay.Server <port>");
            return;
        }

        // port number
        int port = Integer.parseInt(args[0]);

        // create socket
        try (DatagramSocket socket = new DatagramSocket(port))
        {
            Server.runServer(socket);
        }
    }

    public static void runServer(DatagramSocket socket) throws IOException
    {
        while (true)
        {
            // receive request
            byte[] inBuffer = new byte[MAX_BUFFER_SIZE];
            DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);
            socket.receive(inPacket);

            // reply address
            InetAddress relayAddr = inPacket.getAddress();
            int relayPort = inPacket.getPort();

            // send reply
            byte[] outBuffer = inBuffer;
            DatagramPacket outPacket = new DatagramPacket(outBuffer, outBuffer.length, relayAddr, relayPort);
            socket.send(outPacket);
        }
    }
}
