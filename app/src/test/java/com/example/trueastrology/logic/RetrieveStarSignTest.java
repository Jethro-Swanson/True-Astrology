package com.example.trueastrology.logic;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.trueastrology.R;
import com.example.trueastrology.app.Services;
import com.example.trueastrology.persistence.IPredictionPersistence;
import com.example.trueastrology.persistence.IStarSignPersistence;

public class RetrieveStarSignTest {

    @Test
    public void testgetStarSign(){

        System.out.println("Beginning testgetStarSign");
        IStarSignPersistence starSignPersistence = Services.getStarSignPersistence(false);

        IRetrieveStarSign retrieveStarSign = new RetrieveStarSign(starSignPersistence);

        assertNull(retrieveStarSign.getStarSign("abc"));
        assertEquals("Aries", retrieveStarSign.getStarSign("Aries").getSignName());
        assertEquals("Taurus", retrieveStarSign.getStarSign("Taurus").getSignName());
        assertEquals("Gemini", retrieveStarSign.getStarSign("Gemini").getSignName());
        assertEquals("Cancer", retrieveStarSign.getStarSign("Cancer").getSignName());
        assertEquals("Leo", retrieveStarSign.getStarSign("Leo").getSignName());
        assertEquals("Virgo", retrieveStarSign.getStarSign("Virgo").getSignName());
        assertEquals("Libra", retrieveStarSign.getStarSign("Libra").getSignName());
        assertEquals("Scorpio", retrieveStarSign.getStarSign("Scorpio").getSignName());
        assertEquals("Sagittarius", retrieveStarSign.getStarSign("Sagittarius").getSignName());
        assertEquals("Capricorn", retrieveStarSign.getStarSign("Capricorn").getSignName());
        assertEquals("Aquarius", retrieveStarSign.getStarSign("Aquarius").getSignName());
        assertEquals("Pisces", retrieveStarSign.getStarSign("Pisces").getSignName());

        System.out.println("Completed RetrieveStarSignTest");
    }

    @Test
    public void testgetStarSignStringList(){
        System.out.println("Beginning testgetStarSignStringList");
        IStarSignPersistence starSignPersistence = Services.getStarSignPersistence(false);

        IRetrieveStarSign retrieveStarSign = new RetrieveStarSign(starSignPersistence);
        String[] test= retrieveStarSign.getStarSignStringList();

        assertNotNull(test);
        assertTrue(test.length ==12);

        assertTrue(test[0].equals("Aries"));
        assertTrue(test[1].equals("Taurus"));
        assertTrue(test[2].equals("Gemini"));
        assertTrue(test[3].equals("Cancer"));
        assertTrue(test[4].equals("Leo"));
        assertTrue(test[5].equals("Virgo"));
        assertTrue(test[6].equals("Libra"));
        assertTrue(test[7].equals("Scorpio"));
        assertTrue(test[8].equals("Sagittarius"));
        assertTrue(test[9].equals("Capricorn"));
        assertTrue(test[10].equals("Aquarius"));
        assertTrue(test[11].equals("Pisces"));

        System.out.println("Completed testgetStarSignStringList");

    }
}
