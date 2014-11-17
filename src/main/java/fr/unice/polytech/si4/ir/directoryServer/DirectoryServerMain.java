package fr.unice.polytech.si4.ir.directoryServer;


/**
 * Created by Max on 15/11/2014.
 */
public class DirectoryServerMain {

    public static void main(String args[]) {
        DirectoryServer ds = new DirectoryServer();
        System.out.println("Lancement de la méthode principale du serveur");
        if(args.length==0) {
            System.out.println("Le port n'a pas été spécifié, port par défaut: 999");
            ds.launchServer(999);
        }else{
            System.out.println("Port spécifié, lancement sur le port indiqué : "+args[0]);
            ds.launchServer(Integer.parseInt(args[0]));
        }
        System.out.println("Serveur : J'ai fini de travailler");
    }
}
