package rmi.list.a;

import java.rmi.*;

public interface Append extends Remote
{
    //r�ckgabetyp von void zu list ge�ndert
    public List tryToAppend(List l) throws RemoteException;
}
