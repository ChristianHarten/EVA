package rmi.chat;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImplSimple extends UnicastRemoteObject implements ChatClient
{
    private static final long serialVersionUID = 1L;
    private String name;

    public ChatClientImplSimple(String n) throws RemoteException
    {
        name = n;
    }

    public String getName() throws RemoteException
    {
        return name;
    }

    public void print(String msg) throws RemoteException
    {
        System.out.println(msg);
    }
}
