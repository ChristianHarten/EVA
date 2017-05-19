package socket.udp.counter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPSocket implements AutoCloseable
{
    protected DatagramSocket socket;

    private InetAddress addr;

    private int port;

    public UDPSocket() throws SocketException
    {
        this(new DatagramSocket());
    }

    public UDPSocket(int port) throws SocketException
    {
        this(new DatagramSocket(port));
    }

    protected UDPSocket(DatagramSocket socket)
    {
        this.socket = socket;
    }

    public void send(String msg, InetAddress addr1, int port1) throws IOException
    {
        byte[] outBuffer = msg.getBytes();
        DatagramPacket outPacket = new DatagramPacket(outBuffer, outBuffer.length, addr1, port1);
        socket.send(outPacket);
    }

    public String receive(int maxBytes) throws IOException
    {
        byte[] inBuffer = new byte[maxBytes];
        DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);
        socket.receive(inPacket);

        this.addr = inPacket.getAddress();
        this.port = inPacket.getPort();

        return new String(inBuffer, 0, inPacket.getLength());
    }

    public void reply(String msg) throws IOException
    {
        if (addr == null)
        {
            throw new IOException("No Host to reply to");
        }
        send(msg, addr, port);
    }

    public InetAddress getSenderAddress()
    {
        return this.addr;
    }

    public int getSenderPort()
    {
        return this.port;
    }

    public void setTimeout(int timeout) throws SocketException
    {
        socket.setSoTimeout(timeout);
    }

    @Override
    public void close() throws Exception
    {
        socket.close();
    }

}
