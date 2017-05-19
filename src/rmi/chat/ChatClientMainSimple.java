package rmi.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.*;

public class ChatClientMainSimple
{
    public static void main(String[] args)
    {
        if (args.length != 3)
        {
            System.out.println("usage: java " + "rmi.chat.ChatClientMainSimple " + "<server> <nick name> <Chatroom Nr.>");
            return;
        }

        try
        {
            ChatServer server = (ChatServer) Naming.lookup("rmi://" + args[0] + "/ChatServer"+args[2]);
            System.out.println("Server contacted.");

            ChatClientImplSimple client = new ChatClientImplSimple(args[1]);
            if (server.addClient(client))
            {
                System.out.println("End by typing 'exit' or " + "'quit'.");
                sendInputToServer(server, args[1]);
                server.removeClient(client);
            }
            else
            {
                System.out.println("name already defined " + "at server"+args[2]);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.exit(0);
    }

    private static void sendInputToServer(ChatServer server, String name)
    {
        try
        {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = input.readLine()) != null)
            {
                if (line.equals("exit") || line.equals("quit"))
                {
                    break;
                }
                server.sendMessage(name, line);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
