package fr.unice.polytech.si4.ir.MultithreadServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MutiServerThread extends Thread{
	private Socket socket = null;
	private String line;
	public MutiServerThread(Socket socket){
		super("MutiServerThread");
		this.socket = socket;
	}
	
	public void run(){
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
			while(true){
                line=in.readLine();
                out.println(line);
                if(line.equals("OK")){
                    System.out.println("J'ai reçu ok, Je ferme la connection");
                    break;
                }
            }
            out.close();
            in.close();
            socket.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
