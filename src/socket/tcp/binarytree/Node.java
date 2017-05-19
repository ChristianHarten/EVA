package socket.tcp.binarytree;

import java.io.Serializable;

public class Node implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 2L;

    private Node left, right;

    private int value;

    public Node(int value)
    {
        this.value = value;
        this.setLeft(null);
        this.setRight(null);
    }

    public int getValue()
    {
        return this.value;
    }

    public Node getLeft()
    {
        return left;
    }

    public void setLeft(Node left)
    {
        this.left = left;
    }

    public Node getRight()
    {
        return right;
    }

    public void setRight(Node right)
    {
        this.right = right;
    }
}
