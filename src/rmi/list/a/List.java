package rmi.list.a;

import java.io.Serializable;

public class List implements Serializable
{
    private static final long serialVersionUID = 1L;
    private ListItem first, last;

    public void append(int i)
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
    }

    public void print()
    {
        ListItem item = first;
        while (item != null)
        {
            System.out.print(item.getValue() + " ");
            item = item.getNext();
        }
        System.out.println();
    }
}
