package rmi.list.a;

import java.rmi.*;

public interface Append extends Remote
{
    //rückgabetyp von void zu list geändert
    public List tryToAppend(List l) throws RemoteException;
}
