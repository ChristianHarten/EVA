package rmi.ownreg;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Server
{

    public static void main(String[] args) throws IOException
    {
//        System.out.println("Server running on port 1250");
//        CounterImpl counter = new CounterImpl();
//        new Skeleton(1250, counter);
        
        Map<String, CounterImpl> objects = new HashMap<>();
        objects.put("Counter1", new CounterImpl());
        objects.put("Counter2", new CounterImpl());
        objects.put("Counter3", new CounterImpl());
        new Skeleton(1250, objects);
    }
}
