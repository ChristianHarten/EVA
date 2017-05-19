package rmi.list;

import java.rmi.*;

public interface Append extends Remote
{
    public void append(ListInterface l) throws RemoteException;
}