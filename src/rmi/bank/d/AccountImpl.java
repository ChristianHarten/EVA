package rmi.bank.d;

import java.io.Serializable;
import java.rmi.RemoteException;

public class AccountImpl implements Serializable /*
                                                  * extends UnicastRemoteObject
                                                  * implements Account
                                                  */
{
    private static final long serialVersionUID = 1L;

    private double balance;

    public AccountImpl(double balance) throws RemoteException
    {
        this.balance = balance;
    }

    public synchronized double readBalance()
    {
        return balance;
    }

    public synchronized void changeBalance(double v) throws OverdrawAccountException
    {
        if (balance + v < 0)
        {
            throw new OverdrawAccountException();
        }
        balance += v;
    }

}
