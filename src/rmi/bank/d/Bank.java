package rmi.bank.d;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bank extends Remote
{   // rückgabe von account zu accountimpl -> auch in bankimpl ändern
    public AccountImpl getAccount(int numb) throws RemoteException;
}
