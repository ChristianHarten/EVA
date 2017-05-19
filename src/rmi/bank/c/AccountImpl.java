package rmi.bank.c;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AccountImpl extends UnicastRemoteObject implements Account
{
    private static final long serialVersionUID = 1L;

    private double balance;

    public AccountImpl(double balance) throws RemoteException
    {
        this.balance = balance;
    }

    @Override
    public synchronized double readBalance() throws RemoteException
    {
        return balance;
    }

    @Override
    public synchronized void changeBalance(double v) throws RemoteException, OverdrawAccountException
    {
        if (balance + v < 0)
        {
            throw new OverdrawAccountException();
        }
        balance += v;
    }

}
