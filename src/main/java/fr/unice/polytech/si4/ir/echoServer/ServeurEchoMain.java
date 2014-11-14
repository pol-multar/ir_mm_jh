package fr.unice.polytech.si4.ir.echoServer;

/**
 * Created by Max on 10/11/2014.
 */
public class ServeurEchoMain {

    public static void main(String args[]){
        ServerEcho se = new ServerEcho();
        System.out.println("Lancement de la méthode principale du serveur");
        se.launchServer(999);
        System.out.println("Serveur : J'ai fini de travailler");
    }
}
