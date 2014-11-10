package fr.unice.polytech.si4.ir.Server;

import java.net.Socket;

public class MultiServerThreadChaine extends Thread {

    private Socket socket=null;


    public MultiServerThreadChaine(Socket socket){
        super("MultiServerThreadChaine");
        this.socket=socket;
    }

    public void run(){

    }
}
