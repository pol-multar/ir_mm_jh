package fr.unice.polytech.si4.ir.Modele;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author mmultari on 13/11/14.
 */
public class DonneesAnnuaire {

    private Hashtable<String,String> annuaire;//<surnom,nom>

    public DonneesAnnuaire(){
        annuaire=new Hashtable<>();
    }

    public void addEntry(String nom, String surnom){
        if(!annuaire.containsKey(surnom)){
            annuaire.put(surnom,nom);
        }else{
            System.err.println("Surnom déjà utilisé");
        }
    }

    public ArrayList<String> getSurn(String nom){
        Enumeration e=annuaire.keys();
        ArrayList<String> al=new ArrayList<>();
        String key; //Le surnom
        String nomTr;


        while(e.hasMoreElements()){
            key=(String)e.nextElement();
            //System.out.println("J'ai trouvé la clé : "+key);
            nomTr=(String)annuaire.get(key);
            //System.out.println("Elle est associée à :"+nomTr);

            if(nomTr.equals(nom)){
                //System.out.println("J'ajoute la clé trouvée :"+key);
               al.add(key);
            }
        }

        return al;
    }

}
