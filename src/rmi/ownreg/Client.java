package rmi.ownreg;

import java.io.IOException;
import java.net.UnknownHostException;

public class Client
{

    public static void main(String[] args) throws UnknownHostException, IOException
    {
        Stub stub1 = new Stub("localhost", 1250, "Counter1");
        Stub stub2 = new Stub("localhost", 1250, "Counter2");
//        Stub stub3 = new Stub("localhost", 1250, "Counter3");
        
        for (int i = 0; i < 10; i++)
        {
            System.out.println(stub1.increment());
        }
        stub1.close();
        
        for (int i = 0; i < 10; i++)
        {
            System.out.println(stub2.increment());
        }
        stub2.close();
    }
}
