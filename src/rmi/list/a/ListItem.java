package rmi.list.a;

import java.io.Serializable;

public class ListItem implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int value;

    private ListItem next;

    public ListItem(int v)
    {
        value = v;
        next = null;
    }

    public void setNext(ListItem n)
    {
        next = n;
    }

    public int getValue()
    {
        return value;
    }

    public ListItem getNext()
    {
        return next;
    }
}
