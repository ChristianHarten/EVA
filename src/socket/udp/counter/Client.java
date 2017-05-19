package socket.udp.counter;

import java.net.InetAddress;

public class Client
{
    private static int timeout = 2000;

    public static void main(String[] args)
    {
        if (args.length != 3)
        {
            throw new IllegalArgumentException("Usage: java socket.udp.counter.Client <host> <port> <count>");
        }

        int port = Integer.parseInt(args[1]);

        try (UDPSocket socket = new UDPSocket())
        {
            socket.setTimeout(timeout);

            // get Server Address
            InetAddress addr = InetAddress.getByName(args[0]);

            // reset count
            System.out.println("Setting counter to zero");
            socket.send("reset", addr, port);

            // get reply
            String reply = null;
            try
            {
                reply = socket.receive(20);
                System.out.println("counter = " + reply);
            }
            catch (Exception e)
            {
                System.err.println("Cannot receive reply");
            }

            // perform increment count number of times
            System.out.println("incrementing");
            int count = Integer.parseInt(args[2]);
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < count; i++)
            {
                socket.send("increment", addr, port);
                try
                {
                    reply = socket.receive(20);
                }
                catch (Exception e)
                {
                    System.err.println("Cannot receive reply inc");
                }
            }

            long stopTime = System.currentTimeMillis();
            long duration = stopTime - startTime;
            System.out.println("elapsed time = " + duration + " msecs");

            if (count > 0)
            {
                System.out.println("average ping = " + ((duration) / (float) count) + " msecs");
            }
            System.out.println("calculated counter = " + reply);
        }
        catch (Exception e)
        {
            System.err.println("Something wrong boi");
        }
        System.out.println("UDP Socket closed");
    }
}
