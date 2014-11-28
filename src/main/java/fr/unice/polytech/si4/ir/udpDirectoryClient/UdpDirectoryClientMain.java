package fr.unice.polytech.si4.ir.udpDirectoryClient;

/**
 * Created by Max on 28/11/2014.
 */
public class UdpDirectoryClientMain {
    public static void main(String[] args) {
        if(args.length!=2){
            System.out.println("Launching client with default server address and port");
            UdpDirectoryClient client=new UdpDirectoryClient("127.0.0.1",5554);
        } else {
            String hostName = args[0];
            int portNumber = Integer.parseInt(args[1]);
            System.out.println("Launching client, server address : " + hostName + " port number " + portNumber);
            UdpDirectoryClient client=new UdpDirectoryClient(hostName,portNumber);
        }
    }
}
