package fr.unice.polytech.si4.ir.Server;

/**
 * Created by Max on 10/11/2014.
 */
public class ServeurEchoMain {

    public static void main(String args[]){
        ServerEcho se = new ServerEcho();
        se.launchServer(999);
    }
}
