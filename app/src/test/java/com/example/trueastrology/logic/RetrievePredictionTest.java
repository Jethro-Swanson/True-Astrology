package com.example.trueastrology.logic;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.trueastrology.app.Services;
import com.example.trueastrology.objects.Prediction;
import com.example.trueastrology.objects.StarSign;
import com.example.trueastrology.objects.User;
import com.example.trueastrology.persistence.IPredictionPersistence;


public class RetrievePredictionTest {

    @Test
    public void testRetrievePrediction(){

        System.out.println("Beginning RetrievePredictionTest: ");

        IPredictionPersistence predictionPersistence = Services.getPredictionPersistence(false);
        IRetrievePrediction retrievePrediction = new RetrievePrediction(predictionPersistence);

        assertNotNull(RetrievePrediction.getRandPred());
        assertEquals("com.example.trueastrology.objects.Prediction", RetrievePrediction.getRandPred().getClass().getName());

        System.out.println("Completed RetrievePredictionTest");
    }

    @Test
    public void testRandPred(){


        System.out.println("Beginning testRandPred: ");

        assertNotNull(RetrievePrediction.getRandPred());

        System.out.println("Completed testRandPred");

    }

    @Test
    public void testDynamizePrediction(){
        System.out.println("Beginning testDynamizePrediction: ");


        User testUser = new User();
        Prediction testPred1= new Prediction(0,
                "Given you're a# $, you will receive concerning news in the near future", 1);

        String testCompare= "Given you're an Aries, you will receive concerning news in the near future";
        String testOutput= RetrievePrediction.dynamizePrediction(testPred1, testUser);

        assertNotNull(testOutput);
        assertTrue(testCompare.equals(testOutput));

        testUser.setUserStarSign(new StarSign("Taurus"));
        testCompare = "Given you're a Taurus, you will receive concerning news in the near future";
        testOutput = RetrievePrediction.dynamizePrediction(testPred1, testUser);

        assertNotNull(testOutput);
        assertTrue(testCompare.equals(testOutput));

        Prediction testPred2= new Prediction(1, "What an amazing day for a# $ today! Your day will be filled with happiness and optimism! " +
                "You’ll feel like you’re on top of the world the whole day! And just before you go to bed, you’ll stub your pinky toe.", 2);
        testUser.setUserStarSign(new StarSign("Virgo"));
        testCompare = "What an amazing day for a Virgo today! Your day will be filled with happiness and optimism! " +
                "You’ll feel like you’re on top of the world the whole day! And just before you go to bed, you’ll stub your pinky toe.";
        testOutput = RetrievePrediction.dynamizePrediction(testPred2, testUser);

        assertNotNull(testOutput);
        assertTrue(testCompare.equals(testOutput));



        System.out.println("Completed testDynamizePrediction");
    }

    @Test
    public void testGetDynamicPred(){
        User testUser = new User();
        IPredictionPersistence predictionPersistence = Services.getPredictionPersistence(false);
        IRetrievePrediction retrievePrediction = new RetrievePrediction(predictionPersistence);

        String testOutput = retrievePrediction.getDynamicPred(testUser);

        assertNotNull(testOutput);
    }

}
