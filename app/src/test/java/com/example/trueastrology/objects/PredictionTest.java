package com.example.trueastrology.objects;

import org.junit.Test;

import static org.junit.Assert.*;



public class PredictionTest {
    @Test
    public void testPrediction1()
    {
        Prediction prediction;

        System.out.println("Beginning PredictionTest");

        prediction = new Prediction(1, "You will cry", 1);
        assertNotNull(prediction);
        assertEquals(1, prediction.getID());
        assertEquals("You will cry",prediction.getText());

        System.out.println("Completed PredictionTest");
    }
}
