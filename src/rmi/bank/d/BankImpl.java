package rmi.bank.d;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class BankImpl extends UnicastRemoteObject implements Bank
{
    private static final long serialVersionUID = 1L;

    private ArrayList<AccountImpl> accounts;

    protected BankImpl() throws RemoteException
    {
        super();
        accounts = new ArrayList<AccountImpl>(100);
        for (int i = 0; i < 100; i++)
        {
            accounts.add(new AccountImpl(0));
        }
    }

    @Override
    public AccountImpl getAccount(int numb) throws RemoteException
    {
        if (numb < 0 || numb > 99)
        {
            throw new IllegalArgumentException();
        }
        return accounts.get(numb);
    }

}
