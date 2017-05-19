package rmi.chat;

import java.rmi.*;

public class ChatServerMain
{
    public static void main(String[] args)
    {
        try
        {
            ChatServerImpl server0 = new ChatServerImpl();
            Naming.rebind("rmi://localhost/ChatServer0", server0);
            ChatServerImpl server1 = new ChatServerImpl();
            Naming.rebind("rmi://localhost/ChatServer1", server1);
        }
        catch (Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
