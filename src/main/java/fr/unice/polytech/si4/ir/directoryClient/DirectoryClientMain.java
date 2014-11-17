package fr.unice.polytech.si4.ir.directoryClient;

/**
 * Created by Max on 16/11/2014.
 */
public class DirectoryClientMain {

    public static void main (String args[]){
        DirectoryClient directoryClient = new DirectoryClient();

        System.out.println("Launching client");
        directoryClient.launchClient("127.0.0.1",9999);
        //directoryClient.launchClient("192.168.56.101",9999);
        System.out.println("Ending client");
    }
}
