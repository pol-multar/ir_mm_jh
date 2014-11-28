package fr.unice.polytech.si4.ir.udpDirectoryServer;

import fr.unice.polytech.si4.ir.model.DirectoryData;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Max on 28/11/2014.
 */
public class UdpDirectoryServerThreads extends Thread {

    protected DatagramSocket socket;
    protected BufferedReader in;
    private DirectoryData directoryData;

    private Boolean verbose;


    public UdpDirectoryServerThreads(int portNb) throws IOException {
        super("UdpDirectoryServerThreads");
        socket = new DatagramSocket(portNb);
        in = null;
        directoryData = new DirectoryData();
        verbose = true;
        printInfo("Lancement du serveur de l'annuaire");
    }

    public void run() {

        boolean interOut=true;
        String serverResponse=null;
        while (interOut) {
            try {
                byte[] buf = new byte[1024];

                // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                printInfo("Un packet a été reçu");

                //figure out response
                serverResponse = interpretor(buf);
                if("EXITOK".equals(serverResponse)){
                    interOut=false;
                }
                buf=serverResponse.getBytes();

                // send the response to the client at "address" and "port"
                InetAddress address=packet.getAddress();
                int port=packet.getPort();
                packet= new DatagramPacket(buf,buf.length, address,port);
                socket.send(packet);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        socket.close();
    }

    /**
     * Method who execute action according to the client request
     *
     * @param buf a byte array containing the client request
     * @return response of the server
     */
    private String interpretor(byte[] buf) {
        String line = new String(buf);
        String[] s = line.split(":");

        switch (s[0]) {
            case "EXIT":
                printInfo("J'ai reçu un EXIT du client, Je ferme la connection");
                return "EXITOK";
            case "AD":
                printInfo("Le client veut ajouter le nom et les surnoms : " + s[1]);
                return addName(s[1]);
            case "PRINTSNAME":
                printInfo("Le client veut afficher les surnoms associés à : " + s[1]);
                return printsname(s[1]);
            default:
                printErr("Instruction non reconnue : " + s[0]);
                return "UNKNOW";
        }


    }


    /**
     * Method in charge of adding a name and its nicknames in the directory
     *
     * @param s the name to add
     * @return server response
     */

    private String addName(String s) {

        String[] infos = s.split(";");
        String name = infos[0];

        if (!directoryData.containName(name)) {

            for (int i = 1; i < infos.length; i++) {
                if (directoryData.addEntry(name, infos[i])) {
                    printInfo(name + " obtient un nouveau surnom : " + infos[i]);
                } else {
                    printInfo(infos[i] + " est déjà pris");
                }
            }
            return ("ADOK");
        } else {
            printInfo("Le nom existe deja dans l'annuaire");
            return ("ALRDYEX");
        }
    }

    /**
     * Method in charge to print nicknames for a name
     *
     * @param name the name to associate
     * @return server response
     */
    private String printsname(String name) {

        ArrayList<String> nickList;
        String s = "";

        nickList = this.directoryData.getNick(name);
        if (nickList.size() != 0) {
            ListIterator<String> li = nickList.listIterator();

            printInfo(name + " est aussi appelé : ");

            while (li.hasNext()) {
                s += li.next() + " ";
            }

            printInfo(s);
            return s;

        } else {
            printInfo(name + " n'a pas de surnom ");
            return ("NOSNAME");
        }

    }

    /**
     * Method in charge of displaying messages
     *
     * @param s
     */
    private void printInfo(String s) {

        if (verbose) {
            System.out.println("Server : " + s);
        }

    }

    /**
     * Method in charge of displaying errors
     *
     * @param s string to display with the error
     */
    private void printErr(String s) {
        System.err.println("Server : " + s);
    }
}
