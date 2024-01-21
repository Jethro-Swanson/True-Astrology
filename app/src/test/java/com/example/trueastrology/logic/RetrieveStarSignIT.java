package com.example.trueastrology.logic;

import static org.junit.Assert.*;

import com.example.trueastrology.Utilities.testUtilities;
import com.example.trueastrology.objects.StarSign;
import com.example.trueastrology.persistence.db.PredictionPersistenceDB;
import com.example.trueastrology.persistence.db.StarSignPersistenceDB;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class RetrieveStarSignIT {
    private IRetrieveStarSign retrieveStarSign;
    private File tempDB;


    @Before
    public void onStartUp() throws IOException{
        try {
            this.tempDB = testUtilities.makeDbCopy();
            final StarSignPersistenceDB persistenceDB = new StarSignPersistenceDB(this.tempDB.getAbsolutePath().replace(".script", ""));
            this.retrieveStarSign = new RetrieveStarSign(persistenceDB);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetStarSign(){
        System.out.println("Beginning testGetStarSign");
        assertEquals(retrieveStarSign.getStarSign("Aries").getSignName(), "Aries");
        System.out.println("Ending testGetStarSign");
    }

    @Test
    public void testGetStarSignStringList(){
        System.out.println("Beginning testGetStarSignStringList");
        String[] testList = retrieveStarSign.getStarSignStringList();
        assertTrue(testList.length == 12);
        System.out.println("Ending testGetStarSignStringList");
    }

    @After
    public void uponFinishing(){this.tempDB.delete();}
}
