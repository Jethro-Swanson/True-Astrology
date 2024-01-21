package com.example.trueastrology.logic;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.trueastrology.app.Services;
import com.example.trueastrology.objects.Tarot;
import com.example.trueastrology.objects.User;
import com.example.trueastrology.persistence.ITarotPersistence;

public class RetrieveTarotTest {

    @Test
    public void testRetrieveTarot(){
        System.out.println("Beginning RetrieveTarotTest");
        ITarotPersistence tarotPersistence = Services.getTarotPersistence(false);
        IRetrieveTarot retrieveTarot = new RetrieveTarot(tarotPersistence);
        assertNull(RetrieveTarot.getTarotCard("Not a name"));

        assertNotEquals(RetrieveTarot.getRandTarot().getTarotText(), "This is not an existing card");
        assertNotEquals(RetrieveTarot.getRandTarot().getCardNum(), 35);

        assertEquals(RetrieveTarot.getTarotCard("The Fool").getCardNum(), 0);
        assertEquals(RetrieveTarot.getTarotCard("The Magician").getCardNum(), 1);
        assertEquals(RetrieveTarot.getTarotCard("The High Priestess").getCardNum(), 2);
        assertEquals(RetrieveTarot.getTarotCard("The Empress").getCardNum(), 3);
        assertEquals(RetrieveTarot.getTarotCard("The Emperor").getCardNum(), 4);
        assertEquals(RetrieveTarot.getTarotCard("The Hierophant").getCardNum(), 5);
        assertEquals(RetrieveTarot.getTarotCard("The Lovers").getCardNum(), 6);
        assertEquals(RetrieveTarot.getTarotCard("The Chariot").getCardNum(), 7);
        assertEquals(RetrieveTarot.getTarotCard("Strength").getCardNum(), 8);
        assertEquals(RetrieveTarot.getTarotCard("The Hermit").getCardNum(), 9);
        assertEquals(RetrieveTarot.getTarotCard("Wheel of Fortune").getCardNum(), 10);
        assertEquals(RetrieveTarot.getTarotCard("Justice").getCardNum(), 11);
        assertEquals(RetrieveTarot.getTarotCard("The Hanged Man").getCardNum(), 12);
        assertEquals(RetrieveTarot.getTarotCard("Death").getCardNum(), 13);
        assertEquals(RetrieveTarot.getTarotCard("Temperance").getCardNum(), 14);
        assertEquals(RetrieveTarot.getTarotCard("The Devil").getCardNum(), 15);
        assertEquals(RetrieveTarot.getTarotCard("The Tower").getCardNum(), 16);
        assertEquals(RetrieveTarot.getTarotCard("The Star").getCardNum(), 17);
        assertEquals(RetrieveTarot.getTarotCard("The Moon").getCardNum(), 18);
        assertEquals(RetrieveTarot.getTarotCard("The Sun").getCardNum(), 19);
        assertEquals(RetrieveTarot.getTarotCard("Judgement").getCardNum(), 20);
        assertEquals(RetrieveTarot.getTarotCard("The World").getCardNum(), 21);

        String[] test = RetrieveTarot.getTarotStringList();

        assertNotNull(test);
        assertTrue(test.length == 22);


        System.out.println("Completed RetrieveTarotTest");
    }

    @Test
    public void testGetDynamicTarot(){
        System.out.println("Beginning testGetDynamicTarot: ");
        ITarotPersistence tarotPersistence = Services.getTarotPersistence(false);
        IRetrieveTarot testTarot = new RetrieveTarot(tarotPersistence);
        User testUser= new User();
        testUser.setName("John");
        assertNotNull(testTarot.getDynamicTarot(testUser));

        System.out.println("Completed testGetDynamicTarot");
    }


}
