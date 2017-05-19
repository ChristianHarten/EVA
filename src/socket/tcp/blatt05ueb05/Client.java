package socket.tcp.blatt05ueb05;

import java.io.IOException;
import java.net.InetAddress;

public class Client
{
    public static void main(String[] args) throws IOException, Exception
    {
        if (args.length != 3)
        {
            System.out.println("Usage java socket.tcp.counter.Client <host> <port> <count>");
            return;
        }

        InetAddress serverAddress = InetAddress.getByName(args[0]);
        int serverPort = Integer.parseInt(args[1]);
        int numberOfIncrements = Integer.parseInt(args[2]);

        try (
        TCPSocket tcpSocket = new TCPSocket(serverAddress.getHostAddress(), serverPort))
        {
            // reset counter first
            System.out.println("> Resetting counter first");
            tcpSocket.send("reset");
            String reply = tcpSocket.receive();

            // init startTime
            System.out.println("> incrementing");
            long startTime = System.currentTimeMillis();

            // perform increment
            for (int i = 0; i < numberOfIncrements; i++)
            {
                tcpSocket.send("increment");
                reply = tcpSocket.receive();
            }

            // for (int i = 0; i < numberOfIncrements; i++)
            // {
            // reply = tcpSocket.receive();
            // }

            // display stats
            long stopTime = System.currentTimeMillis();
            long duration = stopTime - startTime;
            System.out.println("> elapsed time: " + duration + " msecs");

            if (numberOfIncrements > 0)
            {
                System.out.println("> average ping = " + ((duration) / (float) numberOfIncrements) + " msecs");
            }
            System.out.println("> counter = " + reply);
        }
        System.out.println("> TCP connection closed");
    }
}
