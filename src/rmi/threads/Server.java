package rmi.threads;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Server
{

    public Server()
    {
        runServer();
    }

    private void runServer()
    {
        try
        {
            IntImpl obj1 = new IntImpl();
            
            Naming.rebind("Obj1", obj1);
        }
        catch (RemoteException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Work done");
    }
}
