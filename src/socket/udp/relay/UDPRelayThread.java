package socket.udp.relay;

import java.io.Reader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPRelayThread extends Thread
{
    final static int MAX_BUFFER_SIZE = 100;

    private DatagramSocket socket;

    private DatagramPacket inPacket;

    private InetAddress senderAddr;

    private int senderPort;

    private Reader controlReader;

    public UDPRelayThread(DatagramSocket socket, DatagramPacket inPacket, Reader reader)
    {
        this.setSocket(socket);
        this.setInPacket(inPacket);
        this.setSenderAddr(inPacket.getAddress());
        this.setSenderPort(inPacket.getPort());
        this.setControlReader(reader);
    }

    @Override
    public void run()
    {
        System.out.println("Thread starting");

        System.out.println("Done.");
    }

    public DatagramSocket getSocket()
    {
        return socket;
    }

    public void setSocket(DatagramSocket socket)
    {
        this.socket = socket;
    }

    public DatagramPacket getInPacket()
    {
        return inPacket;
    }

    public void setInPacket(DatagramPacket inPacket)
    {
        this.inPacket = inPacket;
    }

    public InetAddress getSenderAddr()
    {
        return senderAddr;
    }

    public void setSenderAddr(InetAddress senderAddr)
    {
        this.senderAddr = senderAddr;
    }

    public int getSenderPort()
    {
        return senderPort;
    }

    public void setSenderPort(int senderPort)
    {
        this.senderPort = senderPort;
    }

    public Reader getControlReader()
    {
        return controlReader;
    }

    public void setControlReader(Reader controlReader)
    {
        this.controlReader = controlReader;
    }
}
