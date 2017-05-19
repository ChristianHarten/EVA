package rmi.counter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client
{
    public static void main(String[] args)
    {
        if (args.length != 2)
        {
            System.out.println("<server> <count>");
            return;
        }

        try
        {
            Counter counter = (Counter) Naming.lookup("rmi://" + args[0] + "/Counter");
            // CounterImpl counter = (CounterImpl) Naming.lookup("rmi://" +
            // args[0] + "/Counter");
            // --> ClassCastException

            System.out.println("Setting counter to zero");
            counter.reset();

            System.out.println("incrementing");
            int count = Integer.parseInt(args[1]);
            long starttime = System.currentTimeMillis();

            int result = 0, i = 0;
            while (i < count)
            {
                result = counter.increment();
                i++;
            }
            long stoptime = System.currentTimeMillis();
            long duration = stoptime - starttime;
            System.out.println("elapsed time: " + duration + " msecs");

            if (count > 0)
            {
                System.out.println("average ping: " + ((duration) / (float) count) + " msecs");
            }
            System.out.println("counter = " + result);
        }
        catch (MalformedURLException | RemoteException | NotBoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
