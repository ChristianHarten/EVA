package socket.tcp.binarytree;

import java.io.Serializable;

public class BinaryTree implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Node root;

    public BinaryTree()
    {
        this(null);
    }

    public BinaryTree(int rootValue)
    {
        this(new Node(rootValue));
    }

    public BinaryTree(Node root)
    {
        this.root = root;
    }

    public void insert(int elem)
    {
        Node newNode = new Node(elem);
        if (root == null)
        {
            root = newNode;
        }
        else
        {
            Node current = root;
            Node parent;
            while (current != null)
            {
                parent = current;
                if (elem < current.getValue())
                {
                    current = current.getLeft();
                    if (current == null)
                    {
                        parent.setLeft(newNode);
                    }
                }
                else if (elem > current.getValue())
                {
                    current = current.getRight();
                    if (current == null)
                    {
                        parent.setRight(newNode);
                    }
                } // if elem < current.getValue()
            } // while
        } // if root == null
    }// insert

    public void inOrder(Node current)
    {
        if (current != null)
        {
            inOrder(current.getLeft());
            System.out.print(current.getValue() + " ");
            inOrder(current.getRight());
        }
    }

    public Node getRoot()
    {
        return this.root;
    }
}
