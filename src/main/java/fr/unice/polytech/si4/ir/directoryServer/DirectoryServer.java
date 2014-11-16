package fr.unice.polytech.si4.ir.directoryServer;

import fr.unice.polytech.si4.ir.model.DirectoryData;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ListIterator;

public class DirectoryServer {

    private ServerSocket annuaireServeurSocket;
    private Socket clientSocket;
    private String line;
    private BufferedReader is;
    private PrintStream os;
    private Boolean interOut;
    private DirectoryData directoryData;

    /**
     * Default constructor for the directory server
     */
    public DirectoryServer() {
        annuaireServeurSocket = null;
        clientSocket = null;
        line = null;
        is = null;
        os = null;
        interOut = true;
        directoryData = new DirectoryData();
    }

    /**
     * The main method of the server
     * Open network stream and execute main loop
     * @param nbPort the sever port
     */
    public void launchServer(int nbPort) {
        System.out.println("Server : Lancement du serveur de l'annuaire");
        try {
            annuaireServeurSocket = new ServerSocket(nbPort);
        } catch (IOException e) {
            System.out.println("Server : Impossible d'ouvrir le port, erreur : " + e);
        }

        try {
            clientSocket = annuaireServeurSocket.accept();
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintStream(clientSocket.getOutputStream());
            while (interOut) {
                line = is.readLine();
                interOut = interpretor(line, is, os);
            }
            os.close();
            is.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Method who execute action according to the client request
     *
     * @param line the client request
     * @param is   the input stream from the client
     * @param os   the output stream to the client
     * @return while condition
     */
    private boolean interpretor(String line, BufferedReader is, PrintStream os) {

        String[] s = line.split(";");

        switch (s[0]) {
            case "OK":
                System.out.println("Server : J'ai reçu un ok du client, Je ferme la connection");
                os.println("OK");
                return false;
            case "AD":
                System.out.println("Server : Le client veut ajouter le nom : " + s[1]);
                return true;
            case "PRINTSNAME":
                System.out.println("Server : Le client veut afficher les surnoms associés à : " + s[1]);
                printsname(s);
                return true;
            default:
                System.err.println("Server : Instruction non reconnue : " + s[0]);
                os.println(line);
                return true;
        }


    }

    /**
     * Method in charge to print nicknames for a name
     *
     * @param nameAndN the name to associate
     */
    private void printsname(String[] nameAndN) {

        ArrayList<String> nickList;

        nickList = this.directoryData.getNick(nameAndN[1]);
        if (nickList.size() != 0) {
            ListIterator<String> li = nickList.listIterator();

            osPrinter(nameAndN[1] + " est aussi appelé : ");

            while (li.hasNext()) {
                osPrinter(li.next());
            }

        } else {
            osPrinter(nameAndN[1] + " n'a pas de surnom ");
        }

    }

    /**
     * Method in charge of printing strings on the Server outputstream
     * @param s the string to send
     */
    private void osPrinter(String s) {
        os.println(s);
    }

}
