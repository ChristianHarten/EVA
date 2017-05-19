package rmi.list.a;

import java.rmi.*;

public class Server
{
    public static void main(String[] args)
    {
        try
        {
            AppendImpl server = new AppendImpl();
            Naming.rebind("rmi://localhost/AppendServer", server);
        }
        catch (Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
