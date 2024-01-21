package com.example.trueastrology.logic;

import static org.junit.Assert.*;

import com.example.trueastrology.Utilities.testUtilities;
import com.example.trueastrology.objects.Tarot;
import com.example.trueastrology.objects.User;
import com.example.trueastrology.persistence.db.PredictionPersistenceDB;
import com.example.trueastrology.persistence.db.TarotPersistenceDB;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;


public class RetrieveTarotIT {
    private IRetrieveTarot retrieveTarot;
    private File tempDB;

    @Before
    public void onStartUp() throws IOException {
        try {
            this.tempDB = testUtilities.makeDbCopy();
            final TarotPersistenceDB persistenceDB = new TarotPersistenceDB(this.tempDB.getAbsolutePath().replace(".script", ""));
            this.retrieveTarot = new RetrieveTarot(persistenceDB);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetDynamicTarot(){
        System.out.println("Beginning testGetDynamicTarot: ");
        User testUser = new User();
        assertNotNull(retrieveTarot.getDynamicTarot(testUser));
        System.out.println("Ending testGetDynamicTarot");
    }

    @Test
    public void testGetTarotCard(){
        System.out.println("Beginning testGetTarotCard: ");
        Tarot testTarot = RetrieveTarot.getTarotCard("The Fool");
        assertNotNull(testTarot);
        assertEquals(testTarot.getCardName(), "The Fool");
        System.out.println("Ending testGetTarotCard");
    }

    @Test
    public void testGetTarotStringList(){
        System.out.println("Beginning testGetTarotStringList: ");
        String[] cardList = RetrieveTarot.getTarotStringList();
        assertNotNull(cardList);
        assertEquals(cardList.length, 22);
        System.out.println("Ending testGetTarotStringList");
    }

    @After
    public void uponFinishing(){this.tempDB.delete();}
}
