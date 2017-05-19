package socket.tcp.blatt05ueb05;

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

    public void send(String s) throws IOException
    {
        outStream.write(s);
        outStream.flush();
    }

    public String receiveLine() throws IOException
    {
        return inStream.readLine();
    }

    public String receive() throws IOException
    {
        char[] cbuf = new char[1024];
        if (inStream.read(cbuf) == -1)
        {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cbuf.length; i++)
        {
            if (cbuf[i] != 0)
            {
                sb.append(cbuf[i]);
            }
        }
        return new String(sb);
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
        outStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
