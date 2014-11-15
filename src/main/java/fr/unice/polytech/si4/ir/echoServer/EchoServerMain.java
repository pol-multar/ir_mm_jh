package fr.unice.polytech.si4.ir.echoServer;

/**
 * Created by Max on 10/11/2014.
 */
public class EchoServerMain {

    public static void main(String args[]){
        EchoServer se = new EchoServer();
        System.out.println("Lancement de la méthode principale du serveur");
        se.launchServer(999);
        System.out.println("Serveur : J'ai fini de travailler");
    }
}
