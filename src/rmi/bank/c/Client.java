package rmi.bank.c;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client
{
    private int port;

    public Client(int rmiPort, String[] args) throws IllegalArgumentException, OverdrawAccountException
    {
        if (Integer.parseInt(args[1]) < 0 || Integer.parseInt(args[1]) > 99)
        {
            throw new IllegalArgumentException();
        }
        this.port = rmiPort;
        System.out.println("Client RMI-Port: " + port);
        runClient(args);
    }

    private void runClient(String[] args) throws OverdrawAccountException
    {
        try
        {
            Bank myBank = (Bank) Naming.lookup("rmi://localhost:" + port + "/Bank");
            if (args[0].equals("l"))
            {
                System.out.println("Kontostand des Kontos " + args[1] + ": " + myBank.getAccount(Integer.parseInt(args[1])).readBalance());
            }
            else if (args[0].equals("s"))
            {
                myBank.getAccount(Integer.parseInt(args[1])).changeBalance(Double.parseDouble(args[2]));
            }
        }
        catch (MalformedURLException | RemoteException | NotBoundException
        | NumberFormatException e)
        {
            e.printStackTrace();
        }
    }
}