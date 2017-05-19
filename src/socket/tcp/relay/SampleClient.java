package socket.tcp.relay;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SampleClient
{

    public static void main(String[] args) throws IOException
    {
        if (args.length != 2)
        {
            System.out.println("usage java SampleClient <host> <port>");
            return;
        }

        InetAddress host = InetAddress.getByName(args[0]);
        int port = Integer.parseInt(args[1]);

        try (Socket socket = new Socket(host, port))
        {
            OutputStream out = new DataOutputStream(socket.getOutputStream());
            InputStream in = new DataInputStream(socket.getInputStream());
            
            byte[] msg = "Hallo".getBytes();
            System.out.println(new String(msg) + " " + msg.length);
            ((DataOutputStream) out).writeInt(msg.length);
            ((DataOutputStream) out).write(msg);
            
            
            int len = ((DataInputStream) in).readInt();
            byte[] result = new byte[len];
            if (len > 0)
            {
                ((DataInputStream) in).readFully(result);
            }
            
            System.out.println(new String(result));
            
        }
        System.out.println("Sent");
    }
}
