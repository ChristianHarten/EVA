package socket.tcp.mail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ReceiveMail
{
    public static void main(String[] args) throws IOException
    {
        if (args.length != 5)
        {
            System.out.println("usage java ReceiveMail <mailserver> <port> <mails> <username> <passw>");
            return;
        }

        InetAddress mailServer = InetAddress.getByName(args[0]);
        int port = Integer.parseInt(args[1]);
        int mailsToRetrieve = Integer.parseInt(args[2]);
        String username = args[3];
        String passw = args[4];

        try (Socket socket = new Socket(mailServer, port))
        {
            receiveMail(socket, mailsToRetrieve, username, passw);
        }
    }

    private static void receiveMail(Socket socket, int mailsToRetrieve, String username, String passw) throws IOException
    {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println(in.readLine());

        // Login
        out.write("USER " + username);
        out.newLine();

        out.write("PASS " + passw);
        out.newLine();

        for (int i = 1; i <= mailsToRetrieve; i++)
        {
            out.write(String.format("RETR %d", i));
            out.newLine();
        }

        out.write("QUIT");
        out.newLine();
        out.flush();

        String line;
        while ((line = in.readLine()) != null)
        {
            System.out.println(line);
        }

        out.close();
        in.close();
    }
}
