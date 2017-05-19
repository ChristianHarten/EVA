package rmi.ownreg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Skeleton
{
    private int port;

    private CounterImpl counter;

    private Map<String, CounterImpl> objects;

    public Skeleton(int port, CounterImpl counter) throws IOException
    {
        this.port = port;
        this.counter = counter;
        runSkeleton();
    }

    public Skeleton(int port, Map<String, CounterImpl> obj) throws IOException
    {
        this.port = port;
        this.objects = obj;
        runSkeleton();
    }

    private void runSkeleton() throws IOException
    {
        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            System.out.println("Skeleton running on port 1250");
            while (true)
            {
                try (Socket socket = serverSocket.accept())
                {
                    BufferedReader inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter outStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    while (true)
                    {
                        String request = inStream.readLine();
                        if (request != null)
                        {
                            String obj = request.split(" ")[0];
                            request = request.split(" ")[1];
                            switch (request)
                            {
                                case "reset":
                                    outStream.write(String.valueOf(objects.get(obj).reset()));
                                    outStream.newLine();
                                    outStream.flush();
                                    break;
                                case "increment":
                                    outStream.write(String.valueOf(objects.get(obj).increment()));
                                    outStream.newLine();
                                    outStream.flush();
                                default:
                                    break;
                            }
                        }
                        else
                        {
                            System.out.println("Closing");
                            break;
                        }
                    }
                }
            }
        }
    }

    public Counter getCounter()
    {
        return this.counter;
    }

    public int getPort()
    {
        return this.port;
    }
}
