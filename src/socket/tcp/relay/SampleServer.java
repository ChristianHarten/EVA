package socket.tcp.relay;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SampleServer
{
    public static void main(String[] args) throws IOException
    {
        if (args.length != 1)
        {
            System.out.println("usage java SampleServer <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            System.out.println("Server running on port " + port);
            runServer(serverSocket);
        }
    }

    private static void runServer(ServerSocket serverSocket)
    {
        while (true)
        {
            try (Socket socket = serverSocket.accept())
            {
                InputStream in = new DataInputStream(socket.getInputStream());
                OutputStream out = new DataOutputStream(socket.getOutputStream());
                while (true)
                {
                    try
                    {
                        int len = ((DataInputStream) in).readInt();
                        byte[] data = new byte[len];
                        if (len > 0)
                        {
                            ((DataInputStream) in).readFully(data);
                        }

                        System.out.println("Read data from client/relay");

                        ((DataOutputStream) out).writeInt(len);
                        ((DataOutputStream) out).write(data);

                        System.out.println("Sent data to client/relay");
                    }
                    catch (Exception e)
                    {
                        System.out.println("Closing connection");
                        break;
                    }
                }
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
