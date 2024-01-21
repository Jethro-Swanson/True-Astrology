package com.example.trueastrology.objects;

import org.junit.Test;

import static org.junit.Assert.*;



public class StarSignTest {

    @Test
    public void testStarSign1(){
        StarSign starSign;

        System.out.println("Beginning StarSignTest");

        starSign = new StarSign("name");
        assertNotNull(starSign);
        assertEquals("name", starSign.getSignName());
        assertTrue(starSign.isEquals("name"));

        System.out.println("Completed PredictionTest");
    }
}
