package rmi.list.a;

import java.rmi.*;
import java.rmi.server.*;

public class AppendImpl extends UnicastRemoteObject implements Append
{
    private static final long serialVersionUID = 1L;

    public AppendImpl() throws RemoteException
    {
    }
    
    //geänderte Liste zurückgeben
    public List tryToAppend(List l) throws RemoteException
    {
        System.out.print("got list: ");
        l.print();

        l.append(4711);
        System.out.print("list manipulated: ");
        l.print();
        return l;
    }
}
