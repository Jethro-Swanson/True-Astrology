package com.example.trueastrology.objects;


import org.junit.Test;

import static org.junit.Assert.*;

public class TarotTest {

    @Test
    public void testTarot(){
        Tarot tarotTest;

        System.out.println("Beginning TarotTest");

        tarotTest = new Tarot(40, "Star Platinum Za Warudo", "Ora Ora Ora");

        assertNotNull(tarotTest);


        assertEquals("Star Platinum Za Warudo", tarotTest.getCardName());
        assertEquals(40, tarotTest.getCardNum());
        assertEquals("Ora Ora Ora", tarotTest.getTarotText());

        System.out.println("Completed TarotTest");
    }
}
