package rmi.threads;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IntImpl extends UnicastRemoteObject implements Interface1
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected IntImpl() throws RemoteException
    {
        super();
    }

    @Override
    public void int1m1() throws RemoteException
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
