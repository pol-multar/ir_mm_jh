package fr.unice.polytech.si4.ir.udpDirectoryServer;

import java.io.IOException;

/**
 * Created by Max on 28/11/2014.
 */
public class UdpDirectoryServer {
    public static void main(String[] args) throws IOException{
        new UdpDirectoryServerThreads(5554).start();
    }
}
