package rmi.bank.a;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server
{
    private int port;

    public Server(int rmiPort)
    {
        this.port = rmiPort;
        System.out.println("Server RMI-Port: " + port);
        runServer();
    }

    private void runServer()
    {
        try
        {
            // Registry reg = LocateRegistry.createRegistry(port);
            Registry reg = LocateRegistry.getRegistry(port);
            for (int i = 0; i < 100; i++)
            {
                AccountImpl rmiObj = new AccountImpl(i);
                reg.rebind("Konto" + i, rmiObj);
            }
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }

    }
}
