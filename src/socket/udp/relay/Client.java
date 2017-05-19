package socket.udp.relay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client
{
    final static int MAX_BUFFER_SIZE = 100;

    final static int TIMEOUT = 10000;

    public static void main(String[] args) throws IOException
    {
        if (args.length != 2)
        {
            System.out.println("usage: java socket.udp.relay.Client <relay name> <relay port>");
            return;
        }

        // relay addr and port
        InetAddress relayAddr = InetAddress.getByName(args[0]);
        int relayPort = Integer.parseInt(args[1]);

        // create new socket with default port
        try (DatagramSocket socket = new DatagramSocket())
        {
            socket.setSoTimeout(TIMEOUT);
            // read user input
            String line = null;
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            while (!(line = input.readLine()).equals("exit"))
            {
                byte[] outBuffer = new byte[MAX_BUFFER_SIZE];
                outBuffer = line.getBytes();

                DatagramPacket outPacket = new DatagramPacket(outBuffer, outBuffer.length, relayAddr, relayPort);
                socket.send(outPacket);

                try
                {
                    byte[] inBuffer = new byte[MAX_BUFFER_SIZE];
                    DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);
                    socket.receive(inPacket);
                    String s = new String(inPacket.getData(), 0, inPacket.getData().length);
                    System.out.println("Result: " + s);
                }
                catch (Exception e)
                {
                    System.out.println("Oh boi");
                }
            }
        }
    }
}
