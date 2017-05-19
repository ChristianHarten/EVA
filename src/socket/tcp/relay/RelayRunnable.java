package socket.tcp.relay;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class RelayRunnable implements Runnable
{
    private Socket socket;

    private InetAddress server;

    private int port;

    public RelayRunnable(Socket socket, InetAddress server, int port) throws IOException
    {
        this.socket = socket;
        this.server = server;
        this.port = port;
    }

    @Override
    public void run()
    {
        try (Socket s = socket)
        {
            try (Socket serverSocket = new Socket(server, port))
            {
                // server streams
                InputStream serverInputStream = new DataInputStream(serverSocket.getInputStream());
                OutputStream serverOutputStream = new DataOutputStream(serverSocket.getOutputStream());

                // client streams
                InputStream clientInputStream = new DataInputStream(s.getInputStream());
                OutputStream clientOutputStream = new DataOutputStream(s.getOutputStream());

                while (true)
                {
                    // get client message
                    int clientDataLength = ((DataInputStream) clientInputStream).readInt();
                    byte[] clientData = new byte[clientDataLength];
                    if (clientDataLength > 0)
                    {
                        ((DataInputStream) clientInputStream).readFully(clientData);
                    }

                    System.out.println("C=>S");
                    // send client data to server
                    ((DataOutputStream) serverOutputStream).writeInt(clientDataLength);
                    ((DataOutputStream) serverOutputStream).write(clientData);

                    // receiver server data
                    int serverDataLength = ((DataInputStream) serverInputStream).readInt();
                    byte[] serverData = new byte[serverDataLength];
                    if (serverDataLength > 0)
                    {
                        ((DataInputStream) serverInputStream).readFully(serverData);
                    }

                    System.out.println("S=>C");
                    // send server data to client
                    ((DataOutputStream) clientOutputStream).writeInt(serverDataLength);
                    ((DataOutputStream) clientOutputStream).write(serverData);
                }
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }

}
