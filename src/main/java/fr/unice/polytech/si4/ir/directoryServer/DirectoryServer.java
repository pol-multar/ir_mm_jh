package fr.unice.polytech.si4.ir.directoryServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DirectoryServer {

    private ServerSocket annuaireServeurSocket;
    private Socket clientSocket;
    private String line;
    private BufferedReader is;
    private PrintStream os;


    public DirectoryServer(){
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

                String[] s=line.split(";");

                if("AD".equals(s[0])){
                    System.out.println("Tu veux ajouter le nom "+s[1]);
                }

                if("PRINTSNAME".equals(s[0])){
                    System.out.println("Tu veux afficher les surnoms associés à : "+s[1]);
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
