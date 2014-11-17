package fr.unice.polytech.si4.ir.objectDirectoryClient;

/**
 * Created by Max on 17/11/2014.
 */
public class ObjectDirectoryClientMain {

    public static void main (String args[]){
        ObjectDirectoryClient directoryClient = new ObjectDirectoryClient();
        System.out.println("Launching client");
        directoryClient.launchClient("127.0.0.1",9999);
        //directoryClient.launchClient("192.168.56.101",9999);
        System.out.println("Ending client");
    }
}
