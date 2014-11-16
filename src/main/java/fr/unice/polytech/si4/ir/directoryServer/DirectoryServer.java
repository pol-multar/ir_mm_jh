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
    private Boolean verbose;

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
        verbose=true;
    }

    /**
     * The main method of the server
     * Open network stream and execute main loop
     *
     * @param nbPort the sever port
     */
    public void launchServer(int nbPort) {
        printInfo("Lancement du serveur de l'annuaire");
        try {
            annuaireServeurSocket = new ServerSocket(nbPort);
        } catch (IOException e) {
            printErr("Impossible d'ouvrir le port, erreur : " + e.toString());
        }

        try {
            clientSocket = annuaireServeurSocket.accept();
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintStream(clientSocket.getOutputStream());
            while (interOut) {
                line = is.readLine();
                interOut = interpretor(line);
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
     * @return while condition
     */
    private boolean interpretor(String line) {

        String[] s = line.split(";");

        switch (s[0]) {
            case "OK":
                printInfo("J'ai reçu un ok du client, Je ferme la connection");
                os.println("OK");
                return false;
            case "AD":
                printInfo("Le client veut ajouter le nom : " + s[1]);
                addName(s[1]);
                return true;
            case "PRINTSNAME":
                printInfo("Le client veut afficher les surnoms associés à : " + s[1]);
                printsname(s[1]);
                return true;
            default:
                printErr("Instruction non reconnue : " + s[0]);
                os.println(line);
                return true;
        }


    }

    /**
     * Method in charge of adding a name and its nicknames in the directory
     * @param s the name to add
     */
    private void addName(String s) {
        String name = s;

        try {
            while(true){
                line=is.readLine();
                if("END".equals(line)){
                    printInfo("Ajout terminé");
                    break;
                }
                printInfo(name+" obtient un nouveau surnom : "+line);
                directoryData.addEntry(name,line);

            }

        } catch (IOException e) {
            printErr(e.toString());
        }


    }

    /**
     * Method in charge to print nicknames for a name
     *
     * @param name the name to associate
     */
    private void printsname(String name) {

        ArrayList<String> nickList;

        nickList = this.directoryData.getNick(name);
        if (nickList.size() != 0) {
            ListIterator<String> li = nickList.listIterator();

            osPrinter(name + " est aussi appelé : ");

            while (li.hasNext()) {
                osPrinter(li.next());
            }
            

        } else {
            osPrinter(name + " n'a pas de surnom ");
        }

    }

    /**
     * Method in charge of printing strings on the Server outputstream
     *
     * @param s the string to send
     */
    private void osPrinter(String s) {
        os.println(s);
    }

    /**
     * Method in charge of displaying messages
     * @param s
     */
    private void printInfo(String s) {

        if (verbose) {
            System.out.println("Server : "+s);
        }

    }

    /**
     * Method in charge of displaying errors
     *
     * @param s string to display with the error
     */
    private void printErr(String s) {
        System.err.println("Server : "+s);
    }

}
