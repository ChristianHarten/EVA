package socket.udp.counter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class InteractiveClient
{
    private static int timeout = 2000;

    public static void main(String[] args)
    {
        if (args.length != 2)
        {
            System.out.println("Usage java socket.udp.counter.InteractiveClient <host> <port>");
            return;
        }

        int port = Integer.parseInt(args[1]);
        try (UDPSocket socket = new UDPSocket())
        {
            socket.setTimeout(timeout);

            // get Serveraddress
            InetAddress addr = InetAddress.getByName(args[0]);

            // String for reply
            String reply = null;

            System.out.println("Use increment | decrement | set | reset");
            System.out.println("Enter 'exit' to quit");

            // read user input
            String line = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while (!(line = br.readLine()).equals("exit"))
            {
                // check syntax
                if (line.toLowerCase().matches("increment|decrement|set -?[0-9]*|reset"))
                {
                    // send msg
                    socket.send(line, addr, port);
                    try
                    {
                        reply = socket.receive(20);
                        System.out.println(reply);
                    }
                    catch (Exception e)
                    {
                        System.out.println("Ooohh booooi sgeht?");
                    }
                }
                else
                {
                    System.out.println("Use one of the following: increment | decrement | set | reset");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Was für 1 scheiß vong den codigkeit her");
        }
    }
}
