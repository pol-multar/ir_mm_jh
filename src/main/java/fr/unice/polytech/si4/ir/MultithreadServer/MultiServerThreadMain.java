package fr.unice.polytech.si4.ir.MultithreadServer;

import java.io.IOException;
import java.net.ServerSocket;

public class MultiServerThreadMain {
	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket = null;
		boolean listening = true;
		try {
			serverSocket = new ServerSocket(999);
		}
		catch (IOException e){
			System.err.println("Je ne peux pas écouter le port:999");
			System.exit(-1);
		}
		while (listening){
			new MutiServerThread(serverSocket.accept()).start();
			serverSocket.close();
		}
	}
}
