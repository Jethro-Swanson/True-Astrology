package com.example.trueastrology.logic;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import com.example.trueastrology.Utilities.testUtilities;
import com.example.trueastrology.objects.Prediction;
import com.example.trueastrology.objects.User;
import com.example.trueastrology.persistence.db.PredictionPersistenceDB;

public class RetrievePredictionIT {
    private IRetrievePrediction retrievePrediction;
    private File tempDB;


    @Before
    public void onStartUp() throws IOException{
        try {
            this.tempDB = testUtilities.makeDbCopy();
            final PredictionPersistenceDB persistenceDB = new PredictionPersistenceDB(this.tempDB.getAbsolutePath().replace(".script", ""));
            this.retrievePrediction = new RetrievePrediction(persistenceDB);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetRandPred(){
        System.out.println("Beginning testGetRandPred:");
        assertNotNull(RetrievePrediction.getRandPred());
        System.out.println("Ending testGetRandPred");
    }

    @Test
    public void testGetDynamicPred(){
        System.out.println("Beginning testGetDynamicPred: ");
        User testUser = new User();
        assertNotNull(retrievePrediction.getDynamicPred(testUser));
        System.out.println("Ending testGetDynamicPred");
    }

    @Test
    public void testDynamizePrediction(){
        System.out.println("Beginning testDynamizePrediction: ");
        User testUser = new User();
        Prediction testPred = new Prediction(0, "As a# $, you will pass this test", 1);
        assertEquals(RetrievePrediction.dynamizePrediction(testPred, testUser), "As an Aries, you will pass this test");
    }

    @After
    public void uponFinishing(){
        this.tempDB.delete();
    }
}
