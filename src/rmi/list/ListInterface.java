package rmi.list;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ListInterface extends Remote
{

    public void print() throws RemoteException;

    public void append(int i) throws RemoteException;

    public ListItem getFirstListItem() throws RemoteException;

    public int size() throws RemoteException;

}
