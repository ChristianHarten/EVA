package socket.tcp.mail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendMail
{
    private static BufferedWriter bout;

    private static BufferedReader bin;

    public static void main(String[] args) throws NumberFormatException, UnknownHostException, IOException
    {
        if (args.length != 7)
        {
            System.out.println("usage SendMail <mailserver> <port> <sending host> <sendermail> <receivermail> <content> <count>");
            return;
        }

        try (Socket socket = new Socket(args[0], Integer.parseInt(args[1])))
        {
            bout = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            bout.write("HELO " + args[2]);
            bout.newLine();
            bout.flush();

            System.out.println(bin.readLine());

            for (int i = 0; i < Integer.parseInt(args[6]); i++)
            {
                bout.write("MAIL FROM: <" + args[3] + ">");
                bout.newLine();
                bout.flush();
                System.out.println(bin.readLine());

                bout.write("RCPT TO: <" + args[4] + ">");
                bout.newLine();
                bout.flush();
                System.out.println(bin.readLine());

                bout.write("DATA");
                bout.newLine();
                bout.flush();
                System.out.println(bin.readLine());

                bout.write(args[5]);
                bout.newLine();
                bout.write(".");
                bout.newLine();
                bout.flush();
                System.out.println(bin.readLine());
            }
            bout.write("QUIT");
            bout.newLine();
            bout.flush();
            System.out.println("Send Mail");
        }
    }
}
