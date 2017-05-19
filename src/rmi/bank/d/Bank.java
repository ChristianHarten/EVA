package rmi.bank.d;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bank extends Remote
{   // r�ckgabe von account zu accountimpl -> auch in bankimpl �ndern
    public AccountImpl getAccount(int numb) throws RemoteException;
}
