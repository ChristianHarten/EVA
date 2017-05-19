package socket.tcp.scan;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class PortScanner
{

    public static void main(String[] args) throws IOException
    {
        if (args.length != 3)
        {
            System.out.println("usage java PortScanner <host> <low> <high>");
            return;
        }

        InetAddress host = InetAddress.getByName(args[0]);
        int lowPort = Integer.parseInt(args[1]);
        int highPort = Integer.parseInt(args[2]);

        StringBuilder result = new StringBuilder();

        //Thread bzw ThreadPool
        for (int i = lowPort; i <= highPort; i++)
        {
            System.out.println("Testing " + host.getHostName() + " on port " + i);
            Socket socket = null;
            try
            {
                socket = new Socket(host, i);
                result.append("port " + i + " is open\n");
            }
            catch (Exception e)
            {
                result.append("port " + i + " is closed\n");
            }
            if (socket != null)
            {
                socket.close();
            }
        }

        System.out.println(result);
    }

}
