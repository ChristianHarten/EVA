package socket.tcp.counter;

import java.io.IOException;
import java.net.ServerSocket;

public class Server
{

    public static void main(String[] args) throws IOException
    {
        if (args.length != 1)
        {
            System.out.println("Usage java socket.tcp.counter.Server <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);
        try (ServerSocket socket = new ServerSocket(port))
        {
            System.out.println("Server running on port " + port);
            runServer(socket);
        }
    }

    public static void runServer(ServerSocket socket)
    {
        int counter = 0;

        while (true)
        {
            try (TCPSocket tcpSocket = new TCPSocket(socket.accept()))
            {
                while (true)
                {
                    String request = tcpSocket.receiveLine();
                    if (request != null)
                    {
                        if (request.matches("set -?[0-9]*"))
                        {
                            counter = Integer.parseInt(request.substring(4));
                        }
                        else if (request.equals("increment"))
                        {
                            counter++;
                        }
                        else if (request.equals("decrement"))
                        {
                            counter--;
                        }
                        else if (request.equals("reset"))
                        {
                            counter = 0;
                        }
                        String result = String.valueOf(counter);
                        tcpSocket.sendLine(result);
                    }
                    else
                    {
                        System.out.println("Closing connection.");
                        break;
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
    }
}
