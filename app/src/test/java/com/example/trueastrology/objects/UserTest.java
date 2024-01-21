package com.example.trueastrology.objects;

import org.junit.Test;

import static org.junit.Assert.*;


public class UserTest {

    @Test
    public void testUser1(){
        User user1;
        User user2;

        System.out.println("Beginning TestUser");

        //constructor 1
        user1 = new User();
        assertNotNull(user1);
        assertEquals("Aries",user1.getUserStarSign().getSignName());
        assertEquals("NA", user1.getName());
        assertEquals(0,user1.getUserID());
        assertEquals(0,user1.getLogin());
        assertEquals(0,user1.getPredictionCounter());

        //constructor 2
        StarSign starSign = new StarSign("Taurus");
        user2 = new User(starSign, 1, "Jack");
        assertNotNull(user2);
        assertEquals("Taurus",user2.getUserStarSign().getSignName());
        assertEquals("Jack", user2.getName());
        assertEquals(1,user2.getUserID());
        assertEquals(0,user1.getLogin());
        assertEquals(0,user1.getPredictionCounter());

        //mutators
        user1.setUserStarSign(starSign);
        assertEquals("Taurus",user1.getUserStarSign().getSignName());

        user1.incrementLogin();
        user1.incrementLogin();
        assertEquals(2,user1.getLogin());
        user1.decrementLogin();
        assertEquals(1,user1.getLogin());
        user1.resetLogin();
        assertEquals(0,user1.getLogin());

        user1.incrementPred();
        user1.incrementPred();
        assertEquals(2,user1.getPredictionCounter());
        user1.decrementPred();
        assertEquals(1,user1.getPredictionCounter());
        user1.resetPredictionCounter();
        assertEquals(0,user1.getPredictionCounter());

        System.out.println("Completed TestUser");
    }
}
