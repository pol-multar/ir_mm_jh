package fr.unice.polytech.si4.ir.MultithreadClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiThreadClient {
	private Socket directorySocket;
	private PrintWriter os;
	private BufferedReader is,handinput;
	private BufferedReader stdIn;
	private Boolean verbose;

	public MultiThreadClient() {
		directorySocket = null;
		os = null;
		is = null;
		handinput = new BufferedReader(new InputStreamReader(
				System.in));
		stdIn = null;
		verbose = true;
	}

	public void launchClient(String hostName, int portNumber) {

		try {
			directorySocket = new Socket(InetAddress.getByName(hostName),
					portNumber);
			os = new PrintWriter(directorySocket.getOutputStream(), true);
			is = new BufferedReader(new InputStreamReader(
					directorySocket.getInputStream()));
			stdIn = new BufferedReader(new InputStreamReader(System.in));
		} catch (UnknownHostException e) {
			printErr("Je ne connais pas : " + hostName);
			System.exit(1);
		} catch (IOException e) {
			printErr("Ne peux pas I/O pour la connection vers : " + hostName);
			System.exit(1);

		}

		if (directorySocket != null && os != null && is != null
				&& stdIn != null) {
			try {
					sendString("Coucou serveur");
					sendString("J'attend ta réponse");
					askNickName("toto");
					addNickName("toto;riri;fifi;loulou");
					askNickName("toto");
					sendString("ENDCO");

				// attente de la réponse du serveur

				String lineInput;
                while ((lineInput = is.readLine()) != null) {
                    printInfo("Client : Réponse du serveur : " + lineInput);
                    if ("EXITOK".equals(lineInput)) {
                        printInfo("Le serveur termine la connexion, deconnexion");
                        break;
                    }
                    else if("ENDCOK".equals(lineInput)){
                        printInfo("Le serveur accepte notre deconnexion");
                        break;
                    }
                }
                System.out.println("Taper Entrée pour terminer le client");
                String handlineinput = handinput.readLine();
				os.close();
				is.close();
				directorySocket.close();
			} catch (UnknownHostException e) {
				printErr("Trying to connect to unknown host " + hostName);

			} catch (IOException e) {
				printErr("Couldn't get I/O for the connection to " + hostName);
			}

		}
	}

	private void askNickName(String s) {
		sendString("PRINTSNAME:" + s);
	}

	private void addNickName(String s) {
		sendString("AD:" + s);
	}

	private void sendString(String s) {
		os.println(s);
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
