package rmi.list;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class List extends UnicastRemoteObject implements ListInterface
{
    private static final long serialVersionUID = 1L;

    private ListItem first, last;

    private int size;

    public List() throws RemoteException
    {
        super();
        size = 0;
    }

    public synchronized void append(int i)
    {
        if (first == null)
        {
            first = new ListItem(i);
            last = first;
        }
        else
        {
            last.setNext(new ListItem(i));
            last = last.getNext();
        }
        size++;
    }

    public synchronized void print()
    {
        ListItem item = first;
        while (item != null)
        {
            System.out.print(item.getValue() + " ");
            item = item.getNext();
        }
        System.out.println();
    }

    @Override
    public synchronized ListItem getFirstListItem() throws RemoteException
    {
        return first;
    }

    @Override
    public synchronized int size() throws RemoteException
    {
        return size;
    }
}
