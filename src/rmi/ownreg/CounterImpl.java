package rmi.ownreg;

import java.io.Serializable;

public class CounterImpl implements Counter, Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 668804898779064099L;

    private int counter;

    protected CounterImpl()
    {
        super();
        counter = 0;
    }

    @Override
    public synchronized int reset()
    {
        counter = 0;
        return counter;
    }

    @Override
    public synchronized int increment()
    {
        counter++;
        return counter;
    }
}
