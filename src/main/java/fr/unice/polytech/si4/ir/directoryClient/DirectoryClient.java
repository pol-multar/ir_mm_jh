package fr.unice.polytech.si4.ir.directoryClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Max on 10/11/2014.
 */
public class DirectoryClient {
    private Socket echoSocket;
    private DataOutputStream os;
    private DataInputStream is;
    private Boolean verbose;

    public DirectoryClient() {
        echoSocket = null;
        os = null;
        is = null;
        verbose = true;
    }

    public void launchClient(String nameHost, int nbPort) {

        try {
            echoSocket = new Socket(nameHost, nbPort);
            os = new DataOutputStream(echoSocket.getOutputStream());
            is = new DataInputStream(echoSocket.getInputStream());
        } catch (UnknownHostException e) {
            printErr("Je ne connais pas : " + nameHost);
        } catch (IOException e) {
            printErr("Ne peux pas I/O pour la connection : " + nameHost);
        }

        if (echoSocket != null && os != null && is != null) {
            try {
                sendString("Coucou serveur");
                sendString("J'attend ta réponse");
                askNickName("toto");
                addNickName("toto\nriri\nfifi\nloulou\nEND");
                askNickName("toto");
                sendString("OK");

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
        sendString("PRINTSNAME;" + s);
    }

    private void addNickName(String s) {
        sendString("AD;" + s);
    }

    private void sendString(String s) {
        try {
            os.writeBytes(s + "\n");
        } catch (IOException e) {
            System.err.println("IOException : " + e);
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
