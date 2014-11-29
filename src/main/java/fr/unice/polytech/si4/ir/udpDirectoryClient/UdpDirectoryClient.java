package fr.unice.polytech.si4.ir.udpDirectoryClient;

import java.io.IOException;
import java.net.*;

/**
 * Created by Max on 28/11/2014.
 */
public class UdpDirectoryClient {
    private boolean verbose;
    private InetAddress serverAddress;
    private DatagramPacket packet;
    private DatagramSocket socket;
    private int port;
    private byte[] buf;
    private static final int bufLength=1024;

    public UdpDirectoryClient(String hostname, int port) {
        serverAddress = null;
        packet = null;
        socket = null;
        this.port = port;
        buf = new byte[bufLength];
        verbose = true;
        mainClient(hostname);

    }

    public void mainClient(String hostname) {
        try {
            //1. Get a datagram socket
            socket = new DatagramSocket();

            //2. Send request

            serverAddress = InetAddress.getByName(hostname);

            sendPacket("Coucou serveur");
            sendPacket("J'attend ta réponse");
            askNickName("toto");
            addNickName("toto;riri;fifi;loulou");
            askNickName("toto");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void askNickName(String s) {
        sendPacket("PRINTSNAME:" + s);
    }

    private void addNickName(String s) {
        sendPacket("AD:" + s);
    }

    private void sendPacket(String s) {
        try {
            buf = s.getBytes();
            packet = new DatagramPacket(buf, buf.length, serverAddress, port);
            socket.send(packet);

            Thread.sleep(3000);

            //3. Get response
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            //4. Display response
            String receive = new String(packet.getData(), 0, packet.getLength());
            printInfo(receive);

            Thread.sleep(3000);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printInfo(String s) {
        if (verbose) {
            System.out.println(s);
        }
    }

    private void printErr(String s) {
        System.err.println(s);
    }

}
