package uebung03;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DNSLookup
{
    public static void main(String[] args) throws IOException
    {
        InetAddress[][] hosts = new InetAddress[args.length][];

        for (int i = 0; i < args.length; i++)
        {
            try
            {
                hosts[i] = InetAddress.getAllByName(args[i]);
            }
            catch (UnknownHostException e)
            {
            }
        }

        for (int i = 0; i < hosts.length; i++)
        {
            if (hosts[i] != null)
            {
                for (int j = 0; j < hosts[i].length; j++)
                {
                    System.out.print(hosts[i][j].getHostName() + " ====> " + hosts[i][j].getHostAddress() + " is ");
                    System.out.println(hosts[i][j].isReachable(2000) ? " REACHABLE" : " NOT REACHABLE");
                }
                continue;
            }
            System.out.println(args[i] + ": Unknown Host");
        }
    }
}
