package socket.tcp.binarytree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPSocket implements AutoCloseable
{
    private Socket socket;

    private BufferedReader inStream;

    private BufferedWriter outStream;

    public TCPSocket(String address, int port) throws UnknownHostException, IOException
    {
        this(new Socket(address, port));
    }

    public TCPSocket(Socket socket) throws IOException
    {
        this.socket = socket;
        initializeStreams();
    }

    public void sendLine(String s) throws IOException
    {
        outStream.write(s);
        outStream.newLine();
        outStream.flush();
    }

    public String receiveLine() throws IOException
    {
        return inStream.readLine();
    }
    
    public Socket getSocket()
    {
        return this.socket;
    }

    @Override
    public void close() throws Exception
    {
        socket.close();
    }
    
    private void initializeStreams() throws IOException
    {
        System.out.println("Was geht?");
        outStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
