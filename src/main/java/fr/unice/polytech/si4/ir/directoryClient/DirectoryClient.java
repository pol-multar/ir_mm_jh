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

    public DirectoryClient() {
        echoSocket = null;
        os = null;
        is = null;
    }

    public void launchClient(String nameHost, int nbPort) {

        try {
            echoSocket = new Socket(nameHost, nbPort);
            os = new DataOutputStream(echoSocket.getOutputStream());
            is = new DataInputStream(echoSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Je ne connais pas : " + nameHost);
        } catch (IOException e) {
            System.err.println("Ne peux pas I/O pour la connection : " + nameHost);
        }

        if (echoSocket != null && os != null && is != null) {
            try {
                sendString("Coucou serveur");
                sendString("J'attend ta réponse");
                sendString("PRINTSNAME;toto");
                sendString("OK");

                //attente de la réponse du serveur

                String responseLine;
                while ((responseLine = is.readLine()) != null) {
                    System.out.println("Serveur : " + responseLine);
                    if (responseLine.indexOf("OK") != -1) {
                        System.out.println("J'ai reçu le OK du serveur");
                        break;
                    }
                }
                os.close();
                is.close();
                echoSocket.close();
            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host " + e);

            } catch (IOException e) {
                System.err.println("IOException : " + e);
            }

        }
    }

    private void sendString(String s) {
        try {
            os.writeBytes(s + "\n");
        } catch (IOException e) {
            System.err.println("IOException : " + e);
        }
    }
}
