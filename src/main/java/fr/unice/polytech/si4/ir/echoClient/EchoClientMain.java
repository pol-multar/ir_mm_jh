package fr.unice.polytech.si4.ir.echoClient;

/**
 * Created by Max on 10/11/2014.
 */
public class EchoClientMain {

    public static void main(String args[]) {
    EchoClient echoClient =new EchoClient();
        System.out.println("Lancement de la méthode principale du client");
        echoClient.launchClient("localhost",999);
        System.out.println("J'ai fini de travailler");

    }


}
