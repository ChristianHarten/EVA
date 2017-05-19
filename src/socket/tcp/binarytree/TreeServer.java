package socket.tcp.binarytree;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TreeServer
{
    public static void main(String[] args) throws IOException
    {
        if (args.length != 1)
        {
            System.out.println("Usage java socket.tcp.counter.Server <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);
        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            System.out.println("Server running, waiting for client request...");
            runServer(serverSocket);
        }
    }

    private static void runServer(ServerSocket serverSocket)
    {
        while (true)
        {
            try (Socket socket = serverSocket.accept())
            {
                while (true)
                {
                    try (
                    ObjectInputStream oin = new ObjectInputStream(socket.getInputStream()))
                    {
                        BinaryTree treeRead = (BinaryTree) oin.readObject();
                        treeRead.inOrder(treeRead.getRoot());
                        System.out.println();
                        ObjectOutputStream oout = new ObjectOutputStream(socket.getOutputStream());
                        oout.writeObject(treeRead);
                        oout.flush();
                    }
                    catch (SocketException e)
                    {
                        System.out.println("Connection closed");
                        break;
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
