package rmi.bank.d;

public class Test
{
    public static void main(String[] args)
    {
        new Server(1099);
        try
        {
            new Client(1099, new String[]{"l","5"});
            new Client(1099, new String[]{"s","5","100"});
            new Client(1099, new String[]{"l","5"});
            new Client(1099, new String[]{"s","5","20"});
            new Client(1099, new String[]{"l","5"});
        }
        catch (IllegalArgumentException | OverdrawAccountException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
