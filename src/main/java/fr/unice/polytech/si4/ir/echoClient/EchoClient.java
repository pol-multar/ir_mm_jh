package fr.unice.polytech.si4.ir.echoClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Max on 10/11/2014.
 */
public class EchoClient {

    private Socket echoSocket;
    private DataOutputStream os;
    private DataInputStream is;

    public EchoClient() {
        echoSocket = null;
        os = null;
        is = null;
    }

    public void launchClient(String nameHost,int nbPort){

        try {
            echoSocket = new Socket(nameHost, nbPort);
            os= new DataOutputStream(echoSocket.getOutputStream());
            is= new DataInputStream(echoSocket.getInputStream());
        }catch(UnknownHostException e){
            System.err.println("Client : Je ne connais pas : "+nameHost);
        }catch (IOException e){
            System.err.println("Client : Ne peux pas I/O pour la connection : "+nameHost);
        }

        if(echoSocket!=null && os!=null && is !=null){
            try {
                os.writeBytes("Coucou serveur\n");
                os.writeBytes("J'attend ta réponse\n");
                os.writeBytes("OK\n");

                //attente de la réponse du serveur

                String responseLine;
                while((responseLine=is.readLine())!=null){
                    System.out.println("Client : Réponse du serveur : "+responseLine);
                    if(responseLine.indexOf("OK")!=-1){
                        System.out.println("Client : J'ai reçu le OK du serveur");
                        break;
                    }
                }
                os.close();
                is.close();
                echoSocket.close();
            }
            catch (UnknownHostException e){
                System.err.println("Client : Trying to connect to unknown host "+e);

            }
            catch (IOException e){
                System.err.println("IOException : "+e);
            }

        }
    }
}
