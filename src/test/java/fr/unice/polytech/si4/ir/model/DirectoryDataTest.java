package fr.unice.polytech.si4.ir.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author mmultari on 13/11/14.
 */
public class DirectoryDataTest {

    private DirectoryData directoryDataTest;


    public DirectoryDataTest(){

    }

    /**
     * Initialisation des tests
     */

    @Before
    public void setUp(){
        directoryDataTest =new DirectoryData();

    }

    /**
     * Reinitialisation après chaque fin de test
     */

    @After
    public void tearDown(){
    directoryDataTest =null;
    }

    /**
     * Tests d'ajout et de récupération
     */
    @Test
    public void ajoutEtRecup(){
        directoryDataTest.addEntry("toto","riri");
        directoryDataTest.addEntry("toto","fifi");
        directoryDataTest.addEntry("toto","loulou");
        directoryDataTest.addEntry("tata","lala");
        directoryDataTest.addEntry("tata","riri");
        ArrayList<String> al=new ArrayList<>();
        al= directoryDataTest.getNick("toto");
        assertEquals(3,al.size());
    }

}
