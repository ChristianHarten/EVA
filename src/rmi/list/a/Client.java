package rmi.list.a;

import java.rmi.Naming;

public class Client
{
    public static void main(String[] args)
    {
        if (args.length < 2)
        {
            System.out.println("usage: java rmi.list.Client" + " <server>" + " <val1> <val2> ..." + " <valN>");
            return;
        }
        try
        {
            Append server = (Append) Naming.lookup("rmi://" + args[0] + "/AppendServer");
            System.out.println("Server contacted");
            List l = new List();
            for (int i = 1; i < args.length; i++)
            {
                int value = Integer.parseInt(args[i]);
                l.append(value);
            }
            System.out.print("list before RMI: ");
            l.print();
            l= server.tryToAppend(l);
            System.out.print("list after RMI: ");
            l.print();
        }
        catch (Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
