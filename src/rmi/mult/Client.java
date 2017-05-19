package rmi.mult;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client
{
    private int port;
    
    public Client(int port)
    {
        this.port = port;
        doWork();
    }

    private void doWork()
    {
        try
        {
            Multiply mult = (Multiply) Naming.lookup("rmi://localhost:" + port + "/MultiplyServer");

            int i = 1;
            while (i < 11)
            {
                int j = 1;
                while (j < 11)
                {
                    System.out.println("(" + i + " * " + j + ") = " + mult.mult(i, j));
                    j++;
                }
                i++;
            }
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
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Work done");
    }
}
