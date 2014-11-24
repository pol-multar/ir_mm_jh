package fr.unice.polytech.si4.ir.MultithreadServer;

import java.io.IOException;
import java.net.ServerSocket;

public class MultiServerThreadMain {
	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket = null;
		boolean listening = true;
		System.out.println("Lancement de la méthode principale du serveur");
		if(args.length==0) {
            System.out.println("Le port n'a pas été spécifié, port par défaut: 9999");
            System.out.println("Server : Lancement du serveur de l'annuaire");
            try {
    			serverSocket = new ServerSocket(9999);
    		}
    		catch (IOException e){
    			System.err.println("Server : "+"Impossible d'ouvrir le port, erreur : " + e.toString());
    			System.exit(-1);
    		}
    		while (listening){
    			System.out.println("IP : " + serverSocket.getInetAddress() + " : " + serverSocket.getLocalPort());
    			new MutiServerThread(serverSocket.accept()).run();
    			serverSocket.close();
    		}
        }else{
            System.out.println("Port spécifié, lancement sur le port indiqué : "+args[0]);
            System.out.println("Server : Lancement du serveur de l'annuaire");
            try {
    			serverSocket = new ServerSocket(Integer.parseInt(args[0]));
    		}
    		catch (IOException e){
    			System.err.println("Server : "+"Impossible d'ouvrir le port, erreur : " + e.toString());
    			System.exit(-1);
    		}
            while (listening){
            	System.out.println("IP : " + serverSocket.getInetAddress() + " : " + serverSocket.getLocalPort());
    			new MutiServerThread(serverSocket.accept()).run();
    			serverSocket.close();
    		}
        }
		System.out.println("Serveur : J'ai fini de travailler");
	}
}
