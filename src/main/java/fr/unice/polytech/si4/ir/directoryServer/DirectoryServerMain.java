package fr.unice.polytech.si4.ir.directoryServer;


/**
 * Created by Max on 15/11/2014.
 */
public class DirectoryServerMain {

    public static void main(String args[]) {
        DirectoryServer ds = new DirectoryServer();
        System.out.println("Lancement de la méthode principale du serveur");
        ds.launchServer(999);
        System.out.println("Serveur : J'ai fini de travailler");
    }
}
