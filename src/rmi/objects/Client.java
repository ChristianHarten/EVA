package rmi.objects;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@SuppressWarnings("unused")
public class Client
{
    public Client()
    {
        doWork();
    }

    private void doWork()
    {
        try
        {
            // Aufgabe a)
            // String[] names = LocateRegistry.getRegistry().list();
            // for(String name : names)
            // {
            // System.out.println("Name:" + name);
            // }
            // Aufgabe b)
            // Interface1 obj11 = (Interface1)
            // Naming.lookup("rmi://localhost/Obj1");
            // Interface1 obj12 = (Interface1)
            // Naming.lookup("rmi://localhost/Obj1");
            // System.out.println("obj11 == obj12: " + (obj11 == obj12));
            // System.out.println("obj11.equals(obj12): " +
            // (obj11.equals(obj12)));

            // Aufgabe c)
            Interface1 obj21 = (Interface1) Naming.lookup("rmi://localhost/Obj1");
            Interface1 obj22 = (Interface1) Naming.lookup("rmi://localhost/Obj2");
            System.out.println("obj21 == obj22: " + (obj21 == obj22));
            System.out.println("obj21.equals(obj22): " + (obj21.equals(obj22)));
        }
        catch (RemoteException | MalformedURLException | NotBoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Work done");
    }
}
