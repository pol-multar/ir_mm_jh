package fr.unice.polytech.si4.ir.Modele;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author mmultari on 13/11/14.
 */
public class DonneesAnnuaireTest {

    private DonneesAnnuaire donneesAnnuaireTest;


    public DonneesAnnuaireTest(){

    }

    /**
     * Initialisation des tests
     */

    @Before
    public void setUp(){
        donneesAnnuaireTest=new DonneesAnnuaire();

    }

    /**
     * Reinitialisation après chaque fin de test
     */

    @After
    public void tearDown(){
    donneesAnnuaireTest=null;
    }

    /**
     * Tests d'ajout et de récupération
     */
    @Test
    public void ajoutEtRecup(){
        donneesAnnuaireTest.addEntry("toto","riri");
        donneesAnnuaireTest.addEntry("toto","fifi");
        donneesAnnuaireTest.addEntry("toto","loulou");
        donneesAnnuaireTest.addEntry("tata","lala");
        donneesAnnuaireTest.addEntry("tata","riri");
        ArrayList<String> al=new ArrayList<>();
        al=donneesAnnuaireTest.getSurn("toto");
        assertEquals(3,al.size());
    }

}
