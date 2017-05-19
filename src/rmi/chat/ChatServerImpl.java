package rmi.chat;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer
{
    private static final long serialVersionUID = 1L;

    private ArrayList<ChatClient> allClients;

    private LinkedList<String> savedMsgs;

    private final int msgsToSave = 5;

    private int amountSavedMsgs;

    public ChatServerImpl() throws RemoteException
    {
        allClients = new ArrayList<ChatClient>();
        savedMsgs = new LinkedList<String>();
        amountSavedMsgs = 0;
    }

    public synchronized boolean addClient(ChatClient objRef) throws RemoteException
    {
        String name = objRef.getName();
        for (Iterator<ChatClient> iter = allClients.iterator(); iter.hasNext();)
        {
            ChatClient cc = iter.next();
            try
            {
                if (cc.getName().equals(name))
                {
                    return false;
                }
            }
            catch (RemoteException exc)
            {
                iter.remove();
            }
        }
        allClients.add(objRef);
        //like this?
        for (int i=0;i < amountSavedMsgs;i++)
        {
            objRef.print(savedMsgs.get(i));
        }
        sendMessage(name, "joined the chat");
        return true;
    }

    public synchronized void removeClient(ChatClient objRef) throws RemoteException
    {
        allClients.remove(objRef);
        sendMessage(objRef.getName(), "left the Chat");
    }

    public synchronized void sendMessage(String name, String msg) throws RemoteException
    {
        for (Iterator<ChatClient> iter = allClients.iterator(); iter.hasNext();)
        {
            ChatClient cc = iter.next();
            try
            {
                cc.print(name + ": " + msg);
            }
            catch (RemoteException exc)
            {
                iter.remove();
            }
        }
        
        savedMsgs.add(name + ": " + msg);
        amountSavedMsgs++;
        if (amountSavedMsgs > msgsToSave)
        {
            savedMsgs.removeFirst();
            amountSavedMsgs--;
        }
    }
}