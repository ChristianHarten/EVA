package rmi.mult;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
            MultiplyImpl mult = new MultiplyImpl();
            reg.rebind("MultiplyServer", mult);
            System.out.println("multiply server running");
            reg.unbind("MultiplyServer");
            // System.out.println(UnicastRemoteObject.unexportObject(mult,
            // true));
        }
        catch (RemoteException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NotBoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Work done");
    }
}
