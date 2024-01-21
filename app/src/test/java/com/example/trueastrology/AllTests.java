package com.example.trueastrology;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;

import com.example.trueastrology.objects.PredictionTest;
import com.example.trueastrology.objects.StarSignTest;
import com.example.trueastrology.objects.TarotTest;
import com.example.trueastrology.objects.UserTest;
import com.example.trueastrology.logic.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PredictionTest.class,
        UserTest.class,
        StarSignTest.class,
        RetrievePredictionTest.class,
        RetrieveStarSignTest.class,
        RetrievePredictionTest.class, TarotTest.class, RetrieveUserTest.class, RetrieveTarotTest.class
})

public class AllTests{
}

