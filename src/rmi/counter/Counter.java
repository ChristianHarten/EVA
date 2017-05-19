package rmi.counter;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Counter extends Remote // --> Client: ClassCastException
{
    public int reset() throws RemoteException; // --> Server: ExportException, remote object implements illegal interface

    public int increment() throws RemoteException;
}
