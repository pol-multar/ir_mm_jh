package fr.unice.polytech.si4.ir.model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author mmultari on 13/11/14.
 */
public class DirectoryData {

    private Hashtable<String,String> directory;//<surnom,nom>

    /**
     * Public constructor of the directory
     * It initialises an Hashtable
     */
    public DirectoryData(){
        directory =new Hashtable<>();
    }

    /**
     * The method to add a name and its nicknames to the directory
     * @param name the name to add
     * @param nickname one nickname associated
     * @return true if the name has been added
     */
    public Boolean addEntry(String name, String nickname){
        if(!directory.containsKey(nickname)){
            directory.put(nickname,name);
            return true;
        }else{
            System.err.println("Nickname already taken");
            return false;
        }
    }

    /**
     * The method to add multple nickname to a name
     * @param name the name to add
     * @param nicknames the array list of nicknames associated
     */
    public void addBigEntry(String name, ArrayList<String> nicknames){
        for(String s:nicknames){
            addEntry(name,s);
        }

    }

    /**
     * The method to get the associated nicknames of a name
     * @param name the name that you wanted to know nicknames
     * @return An arrayList of the nickname associated to the name
     */
    public ArrayList<String> getNick(String name){
        Enumeration e= directory.keys();
        ArrayList<String> al=new ArrayList<>();
        String key; //Le surnom
        String assocName;


        while(e.hasMoreElements()){
            key=(String)e.nextElement();
            //System.out.println("J'ai trouvé la clé : "+key);
            assocName=(String) directory.get(key);
            //System.out.println("Elle est associée à :"+assocName);

            if(assocName.equals(name)){
                //System.out.println("J'ajoute la clé trouvée :"+key);
               al.add(key);
            }
        }

        return al;
    }

}
