package socket.tcp.binarytree;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TreeClient
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        if (args.length != 2)
        {
            System.out.println("Usage java socket.tcp.counter.Client <host> <port>");
            return;
        }

        BinaryTree tree = new BinaryTree();
        tree.insert(25);
        tree.insert(2);
        tree.insert(10);
        tree.insert(30);
        tree.insert(26);
        tree.insert(7);
        tree.insert(28);
        tree.insert(99);
        tree.insert(11);
        tree.insert(6);
        tree.insert(42);
        tree.insert(54);
        tree.insert(24);
        tree.insert(12);
        tree.insert(60);
        tree.insert(73);
        tree.insert(14);
        tree.insert(3);
        tree.insert(8);
        tree.insert(49);
        tree.insert(33);
        tree.insert(66);
        tree.insert(43);
        tree.insert(55);
        tree.inOrder(tree.getRoot());
        System.out.println();

        InetAddress host = InetAddress.getByName(args[0]);
        int port = Integer.parseInt(args[1]);

        try (Socket socket = new Socket(host, port))
        {
            ObjectOutputStream oout = new ObjectOutputStream(socket.getOutputStream());
            oout.writeObject(tree);
            oout.flush();

            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            BinaryTree treeRead = (BinaryTree) oin.readObject();
            treeRead.inOrder(treeRead.getRoot());
            System.out.println();

            System.out.println("Connection closed");
        }
    }
}
