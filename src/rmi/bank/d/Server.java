package rmi.bank.d;

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
            BankImpl rmiObj = new BankImpl();
            reg.rebind("Bank", rmiObj);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }

    }
}
