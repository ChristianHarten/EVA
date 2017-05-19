package socket.udp.counter;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Server
{

    private static int counter;

    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Usage: java socket.udp.counter.Server <portNumber>");
        }

        int port = Integer.parseInt(args[0]);

        try (DatagramSocket socket = new DatagramSocket(port))
        {
            runServer(socket);
        }
        catch (SocketException se)
        {

        }
    }

    public static void runServer(DatagramSocket socket)
    {
        try (UDPSocket udpSocket = new UDPSocket(socket))
        {
            // wait for requests
            System.out.println("Server running, waiting for requests.");

            while (true)
            {
                // receive request
                String s = udpSocket.receive(20).toLowerCase();

                if (s.matches("set -?[0-9]*"))
                {
                    counter = Integer.parseInt(s.substring(4));
                    System.out.println("Counter set by " + udpSocket.getSenderAddress() + ", " + udpSocket.getSenderPort());
                }
                else if (s.equals("reset"))
                {
                    counter = 0;
                    System.out.println("Counter resetted by " + udpSocket.getSenderAddress() + ", " + udpSocket.getSenderPort());
                }
                else if (s.equals("decrement"))
                {
                    counter--;
                    System.out.println("Counter decremented by " + udpSocket.getSenderAddress() + ", " + udpSocket.getSenderPort());
                }
                else if (s.equals("increment"))
                {
                    counter++;
                    System.out.println("Counter incremented by " + udpSocket.getSenderAddress() + ", " + udpSocket.getSenderPort());
                }

                String answer = "Counter = " + String.valueOf(counter);
                udpSocket.reply(answer);
            }
        }
        catch (Exception e)
        {
            System.err.println("Fehler bei Handling des UDP Packets");
            e.printStackTrace();
        }
        System.out.println("UDP Socket closed!");
    }
}
