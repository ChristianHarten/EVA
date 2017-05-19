package rmi.mult;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MultiplyImpl extends UnicastRemoteObject implements Multiply
{
    /**
     * 
     */
    private static final long serialVersionUID = 7048343857843626746L;

    public MultiplyImpl() throws RemoteException
    {
        super();
    }

    @Override
    public int mult(int x, int y) throws RemoteException
    {
        return (x * y);
    }
}
