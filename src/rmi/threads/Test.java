package rmi.threads;

public class Test
{

    public static void main(String[] args)
    {
        new Server();
        for (int i = 0; i < 15; i++)
        {
            new Client();
        }
        new Client();
        System.out.println("Work done");
    }

}
