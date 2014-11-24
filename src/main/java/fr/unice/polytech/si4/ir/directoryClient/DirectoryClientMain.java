package fr.unice.polytech.si4.ir.directoryClient;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Created by Max on 16/11/2014.
 */
public class DirectoryClientMain {

    public static void main(String args[]) {
        DirectoryClient directoryClient = new DirectoryClient();

        if (args.length != 2) {
            System.out.println("Launching client with default server address and port");
            directoryClient.launchClient("127.0.0.1", 9999);
            //directoryClient.launchClient("192.168.56.101",9999);

        } else {
            String hostName = args[0];
            int portNumber = Integer.parseInt(args[1]);
            System.out.println("Launching client, server address : " + hostName + " port number " + portNumber);
            directoryClient.launchClient(hostName, portNumber);

        }
        System.out.println("Ending client");
    }
}
