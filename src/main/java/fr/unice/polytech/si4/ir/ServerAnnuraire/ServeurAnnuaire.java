package fr.unice.polytech.si4.ir.ServerAnnuraire;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurAnnuaire {

    private ServerSocket annuaireServeurSocket;
    private Socket clientSocket;
    private String line;
    private BufferedReader is;
    private PrintStream os;


    public ServeurAnnuaire(){
        annuaireServeurSocket=null;
        clientSocket=null;
        line=null;
        is=null;
        os=null;
    }

    public void launchServer(int nbPort){
        System.out.println("Lancement du serveur de l'annuaire");
        try{
            annuaireServeurSocket=new ServerSocket(nbPort);
        }
        catch (IOException e){
            System.out.println("Impossible d'ouvrir le port, erreur : " + e);
        }

        try{
            clientSocket=annuaireServeurSocket.accept();
            is=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os=new PrintStream(clientSocket.getOutputStream());
            while(true){
                line=is.readLine();
                os.println(line);
                if(line.equals("OK")){
                    System.out.println("J'ai reçu ok, Je ferme la connection");
                    break;
                }
            }
            os.close();
            is.close();
            clientSocket.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }



}
