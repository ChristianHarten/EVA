package socket.udp.relay;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPRelay
{
    final static int MAX_BUFFER_SIZE = 100;

    public static void runUDPRelay(DatagramSocket socket, InetAddress addressToRelayTo, int portToRelayTo, Reader controlReader) throws IOException
    {
        // TO-DO Threads und Serverabfrage
        while (true)
        {
            // receive client request
            byte[] inBuffer = new byte[100];
            DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);
            socket.receive(inPacket);
            System.out.println("DEBUG> Received Packet from Client. Send to Server? j/n");

            int firstChoice = controlReader.read();
            if (firstChoice == 106)
            {
                // remember the client
                InetAddress clientAddress = inPacket.getAddress();
                int clientPort = inPacket.getPort();

                // new socket for server communication
                try (DatagramSocket serverSocket = new DatagramSocket())
                {
                    // send client´s request to server
                    byte[] outBufferToServer = inPacket.getData();
                    DatagramPacket outPacketToServer = new DatagramPacket(outBufferToServer, outBufferToServer.length, addressToRelayTo, portToRelayTo);
                    serverSocket.send(outPacketToServer);

                    // receive server´s reply
                    byte[] inBufferServerReply = new byte[MAX_BUFFER_SIZE];
                    DatagramPacket inPacketServerReply = new DatagramPacket(inBufferServerReply, inBufferServerReply.length);
                    serverSocket.receive(inPacketServerReply);

                    // send server reply back to client
                    byte[] outBufferServerReply = inPacketServerReply.getData();
                    DatagramPacket outPacketServerReply = new DatagramPacket(outBufferServerReply, outBufferServerReply.length, clientAddress, clientPort);
                    socket.send(outPacketServerReply);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        if (args.length != 3)
        {
            System.out.println("usage: java socket.udp.relay.UDPRelay <port> <server addr> <server port>");
            return;
        }

        int inPort = Integer.parseInt(args[0]);
        InetAddress addressToRelayTo = InetAddress.getByName(args[1]);
        int portToRelayTo = Integer.parseInt(args[2]);
        Reader controlReader = new InputStreamReader(System.in);

        try (DatagramSocket dgSocket = new DatagramSocket(inPort))
        {
            UDPRelay.runUDPRelay(dgSocket, addressToRelayTo, portToRelayTo, controlReader);
        }
    }
}
