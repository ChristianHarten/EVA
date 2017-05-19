package rmi.interfaces;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IntImpl extends UnicastRemoteObject implements Interface1,Interface2
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
    public void int2m1() throws RemoteException
    {
        System.out.println("int1m1");
    }

    @Override
    public void int1m1() throws RemoteException
    {
        System.out.println("int1m1");
    }

}
