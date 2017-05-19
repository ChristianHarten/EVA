package rmi.counter;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

public class CounterImpl extends UnicastRemoteObject implements Counter
{
    /**
     * 
     */
    private static final long serialVersionUID = 668804898779064099L;

    private int counter;

    protected CounterImpl() throws RemoteException //--> Exception muss behandelt werden (super aufruf)
    {
        super();
        counter = 0;
    } //--> Aufforderung, Konstruktor zu schreiben, der Exception behandelt

    @Override
    public synchronized int reset() throws RemoteException //--> np
    {
        String host =
        null;
        try
        {
            host = RemoteServer.getClientHost();
        }
        catch (ServerNotActiveException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(host);
        counter = 0;
        return counter;
    }

    @Override
    public synchronized int increment() throws RemoteException
    {
        String host =
        null;
        try
        {
            host = RemoteServer.getClientHost();
        }
        catch (ServerNotActiveException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(host);
        counter++;
        return counter;
    }
}
