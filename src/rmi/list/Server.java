package rmi.list;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server
{
    private int port;

    public Server(int port)
    {
        this.port = port;
        runServer();
    }

    private void runServer()
    {
        try
        {
            Registry reg = LocateRegistry.createRegistry(port);
            AppendImpl server = new AppendImpl();
            reg.rebind("AppendServer", server);
            UnicastRemoteObject.unexportObject(server, true);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }
}