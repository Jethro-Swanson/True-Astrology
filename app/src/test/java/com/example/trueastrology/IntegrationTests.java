package com.example.trueastrology;

import com.example.trueastrology.logic.RetrievePredictionIT;
import com.example.trueastrology.logic.RetrieveStarSignIT;
import com.example.trueastrology.logic.RetrieveTarotIT;
import com.example.trueastrology.logic.RetrieveUserIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RetrievePredictionIT.class,
        RetrieveUserIT.class,
        RetrieveTarotIT.class,
        RetrieveStarSignIT.class
})

public class IntegrationTests {
}
