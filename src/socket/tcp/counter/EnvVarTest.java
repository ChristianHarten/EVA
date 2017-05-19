package socket.tcp.counter;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class EnvVarTest
{
    static String userHomeDir = System.getenv("userprofile".toLowerCase()) + "\\testFile.txt";

    public static void main(String[] args) throws SocketException
    {
        Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
        while (e.hasMoreElements())
        {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration<InetAddress> ee = n.getInetAddresses();
            while (ee.hasMoreElements())
            {
                InetAddress i = (InetAddress) ee.nextElement();
                System.out.println(i.getHostAddress());
            }
        }
    }

    // private static void fetchEnvVar() throws IOException
    // {
    // try (
    // BufferedWriter bw = new BufferedWriter(new FileWriter(userHomeDir)))
    // {
    // Map<String, String> envVar = System.getenv();
    // for (Map.Entry<String, String> entry : envVar.entrySet())
    // {
    // String value = entry.getValue();
    // bw.write(value);
    // bw.newLine();
    // }
    // String OS = System.getProperty("os.name");
    // System.out.println(OS);
    //
    // System.getProperties().list(System.out);
    // }
    // }
}
