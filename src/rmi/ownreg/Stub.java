package rmi.ownreg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Stub
{
    private String host, objectName;

    private int port;

    private BufferedReader inStream;

    private BufferedWriter outStream;

    private Socket socket;

    public Stub(String host, int port, String objectName) throws UnknownHostException, IOException
    {
        this.host = host;
        this.port = port;
        this.objectName = objectName;
        runStub();
    }

    private void runStub() throws UnknownHostException, IOException
    {
        try
        {
            socket = new Socket(host, port);
            outStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            close();
        }
    }

    public void close() throws IOException
    {
        socket.close();
    }

    public int reset() throws IOException
    {
        outStream.write(objectName + " reset");
        outStream.newLine();
        outStream.flush();
        String value = inStream.readLine();
        return Integer.parseInt(value);
    }

    public int increment() throws IOException
    {
        outStream.write(objectName + " increment");
        outStream.newLine();
        outStream.flush();
        String value = inStream.readLine();
        return Integer.parseInt(value);
    }
}
