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
    private PrintWriter os;
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
        System.out.println("IP : " + annuaireServeurSocket.getInetAddress() + " : " + annuaireServeurSocket.getLocalPort());

        try {
            clientSocket = annuaireServeurSocket.accept();
            printInfo("Un client s'est connecté");
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintWriter(clientSocket.getOutputStream(),true);
            //osPrinter("Client connecté");
            while (interOut) {
                line = is.readLine();
                interOut = interpretor(line);
            }
            os.close();
            is.close();
            clientSocket.close();
            //TODO ne pas s'arreter ici pour etre pret a recevoir nouveau client
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
            case "EXIT":
                printInfo("J'ai reçu un EXIT du client, Je ferme la connection");
                os.println("EXITOK");
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
                os.println("UNKNOW");
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
                    os.println("ADOK");
                    break;
                }else {
                    printInfo(name + " obtient un nouveau surnom : " + line);
                    directoryData.addEntry(name, line);
                    os.println("SURN");
                }

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
        String s="";

        nickList = this.directoryData.getNick(name);
        if (nickList.size() != 0) {
            ListIterator<String> li = nickList.listIterator();

            //osPrinter(name + " est aussi appelé : ");
            printInfo(name + " est aussi appelé : ");

            while (li.hasNext()) {
                s+=li.next()+" ";
            }
            osPrinter(s);
            printInfo(s);


        } else {
            osPrinter("NOSNAME");
            printInfo(name + " n'a pas de surnom ");
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
