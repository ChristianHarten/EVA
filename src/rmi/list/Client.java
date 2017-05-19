package rmi.list;

import java.net.MalformedURLException;
import java.rmi.*;

public class Client
{
    private int port;

    public Client(int port)
    {
        this.port = port;
        runClient();
    }

    private void runClient()
    {
        try
        {
            Append server = (Append) Naming.lookup("rmi://localhost:" + port + "/AppendServer");
            List l = new List();
            for (int i = 1; i < 10; i++)
            {
                int value = i;
                l.append(value);
            }
            System.out.print("list before RMI: ");
            l.print();
            server.append(l);
            System.out.print("list after RMI: ");
            l.print();
        }
        catch (MalformedURLException | RemoteException | NotBoundException e)
        {
            e.printStackTrace();
        }
    }
}