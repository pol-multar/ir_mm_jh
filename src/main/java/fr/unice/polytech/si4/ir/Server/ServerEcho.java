package fr.unice.polytech.si4.ir.Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Max on 10/11/2014.
 *
 * Un serveur similaire à echo (port 7)
 * Reçoit un texte du client et le renvoie identique
 * Le serveur gère un seul client
 */
public class ServerEcho {
    ServerSocket echoServer;
    String line;
    DataInputStream is;
    PrintStream os;
    Socket clientSocket;

    public ServerEcho(){
        echoServer=null;
        line=null;
        is=null;
        os=null;
        clientSocket=null;
    }

    public void setEchoServer(ServerSocket serverSocket){
        echoServer=serverSocket;
    }

    public void setLine(String s){
        line=s;
    }

    public void setIs(DataInputStream dataInputStream){
        is=dataInputStream;
    }

    public void setOs(PrintStream printStream){
        os=printStream;
    }

    public void setClientSocket(Socket socket){
        clientSocket=socket;
    }

    public void launchServer(int nb){
        try{
            echoServer=new ServerSocket(nb);
        }
        catch (IOException e){
            System.out.println("Impossible d'ouvrir le port, erreur : " + e);
        }

        try{
            clientSocket=echoServer.accept();
            is=new DataInputStream(clientSocket.getInputStream());
            os=new PrintStream(clientSocket.getOutputStream());
            while(true){
                line=is.readLine();
                os.println(line);
            }
        }
        catch(IOException e){
            System.out.println(e);
        }

    }

}
