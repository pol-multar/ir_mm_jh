package fr.unice.polytech.si4.ir.objectDirectoryClient;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Max on 17/11/2014.
 */
public class ObjectDirectoryClient {
    private Socket echoSocket;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private Boolean verbose;
    private Message message;

    public ObjectDirectoryClient() {
        echoSocket = null;
        os = null;
        is = null;
        verbose = true;
    }

    public void launchClient(String nameHost, int nbPort) {

        try {
            echoSocket = new Socket(InetAddress.getByName(nameHost), nbPort);

            os = new ObjectOutputStream(echoSocket.getOutputStream());
            is = new ObjectInputStream(echoSocket.getInputStream());
        } catch (UnknownHostException e) {
            printErr("Je ne connais pas : " + nameHost+"\n"+e.getMessage());
        } catch (IOException e) {
            printErr("Ne peux pas I/O pour la connection : " + nameHost +"\n"+ e.getMessage());

        }

        if (echoSocket != null && os != null && is != null) {
            try {
                sendObject(new Message("Coucou serveur"));
                sendObject(new Message("J'attend ta réponse"));
                askNickName("toto");
                addNickName("toto\nriri\nfifi\nloulou");
                askNickName("toto");
                sendString("EXIT");

                //attente de la réponse du serveur

                String responseLine;
                while ((responseLine = is.readLine()) != null) {
                    printInfo("Client : Réponse du serveur : " + responseLine);
                    if (responseLine.indexOf("OK") != -1) {
                        printInfo("J'ai reçu le OK du serveur");
                        break;
                    }
                }
                os.close();
                is.close();
                echoSocket.close();
            } catch (UnknownHostException e) {
                printErr("Trying to connect to unknown host " + e);

            } catch (IOException e) {
                printErr("IOException : " + e);
            }

        }
    }

    private void askNickName(String s) {
        //sendString("PRINTSNAME;" + s);
        sendObject(new Message ("PRINTSNAME"));
        sendObject(new Message(s));
        sendObject(new Message("END"));
    }

    private void addNickName(String s) {
        //sendString("AD;" + s);
        sendObject(new Message("ADD"));
        String[] tab = s.split("\n");
        for(int i=0;i<tab.length;i++){
            sendObject(new Message(tab[i]));
        }
        sendObject(new Message("END"));
    }

    private void sendString(String s) {
        try {
            os.writeBytes(s + "\n");
        } catch (IOException e) {
            System.err.println("IOException : " + e);
        }
    }

    private void sendObject(Message m){
        try {
            os.writeObject(m);
        } catch (IOException e) {
            printErr("Erreur lors de l'écriture de l'objet"+m.toString()+"\n"+e.getMessage());
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
