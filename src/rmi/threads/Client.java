package rmi.threads;

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
//            String[] names = LocateRegistry.getRegistry().list();
//            for(String name : names)
//            {
//                System.out.println("Name:" + name);
//            }
            // Aufgabe b)
//            Interface1 obj11 = (Interface1) Naming.lookup("rmi://localhost/Obj1");
//            Interface1 obj12 = (Interface1) Naming.lookup("rmi://localhost/Obj1");
//            System.out.println("obj11 == obj12: " + (obj11 == obj12));
//            System.out.println("obj11.equals(obj12): " + (obj11.equals(obj12)));
            
            // Aufgabe c)
            Interface1 obj21 = (Interface1) Naming.lookup("rmi://localhost/Obj1");
//            for (int i = 0; i < 15; i++)
//            {
//                new Thread(() -> {
//                    try
//                    {
//                        obj21.int1m1();
//                    }
//                    catch (RemoteException e)
//                    {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }).start();;
//            }
            obj21.int1m1();
        }
        catch (RemoteException | MalformedURLException | NotBoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Work done");
    }
}
