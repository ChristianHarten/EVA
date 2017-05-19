package socket.tcp.relay;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TCPRelay
{
    private static InetAddress remoteAddress;

    private static ThreadPoolExecutor pool;

    private static int remotePort;

    public static void main(String[] args) throws IOException
    {
        if (args.length != 3)
        {
            System.out.println("usage java TCPRelay <port> <remoteaddress> <remoteport>");
            return;
        }

        int port = Integer.parseInt(args[0]);
        remoteAddress = InetAddress.getByName(args[1]);
        remotePort = Integer.parseInt(args[2]);
        pool = new ThreadPoolExecutor(2, 4, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(2));

        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            System.out.println("Relay running on port " + port);
            runRelay(serverSocket);
        }
    }

    private static void runRelay(ServerSocket serverSocket)
    {
        while (true)
        {
            try 
            {
                Socket socket = serverSocket.accept();
                System.out.println("Eins");
                pool.execute(new RelayRunnable(socket, remoteAddress, remotePort));
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
