package fr.unice.polytech.si4.ir.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by Max on 10/11/2014.
 */
public class ClientEchoMain {

    public static void main(String args[]) {
    ClientEcho clientEcho=new ClientEcho();

        clientEcho.launchClient("localhost",999);


    }


}
