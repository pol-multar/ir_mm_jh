package fr.unice.polytech.si4.ir.clientEcho;

/**
 * Created by Max on 10/11/2014.
 */
public class ClientEchoMain {

    public static void main(String args[]) {
    ClientEcho clientEcho=new ClientEcho();
        System.out.println("Lancement de la méthode principale du client");
        clientEcho.launchClient("localhost",999);
        System.out.println("J'ai fini de travailler");

    }


}
