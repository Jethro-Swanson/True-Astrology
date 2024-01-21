package com.example.trueastrology.srys;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.trueastrology.R;
import com.example.trueastrology.app.Services;
import com.example.trueastrology.objects.Tarot;
import com.example.trueastrology.persistence.ITarotPersistence;
import com.example.trueastrology.persistence.IUserPersistence;
import com.example.trueastrology.ui.LoginPage;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TarotCardTest {

    @Rule
    public ActivityScenarioRule<LoginPage> activityRule = new ActivityScenarioRule<LoginPage>(LoginPage.class);

    @Before
    public void setupDatabase(){
        IUserPersistence userPersistence = Services.getUserPersistence(true);
        userPersistence.getUser().resetUser();
    }

    /*
       Since tarot card was randomly generated, added a test tarot card and deleted everything else
       to make sure it is the only one that gets displayed.
     */
    @Test
    public void tarotTest(){
        ITarotPersistence tarotPersistence = Services.getTarotPersistence(true);
        ArrayList<Tarot> testList=tarotPersistence.getTarotList();

        activityRule.getScenario().close();
        tarotPersistence.deleteAll(testList);
        ActivityScenario.launch(LoginPage.class);

        //added the test-tarot
        Tarot testTarot= new Tarot(22,"TestCard","This is a test Card");
        tarotPersistence.insertTarot(testTarot);


        onView(withId(R.id.name_text)).perform(click());
        onView(withId(R.id.name_text)).perform(clearText());
        onView(withId(R.id.name_text)).perform(typeText("TestUser"));
        closeSoftKeyboard();
        onView(withId(R.id.submit_button)).perform(click());

        onView(withId(R.id.tarot_button)).perform(click());
        onView(withId(R.id.TarotPredictionText)).check(matches(withText("This is a test Card")));

        onView(withId(R.id.back)).perform(click());
        onView(withId(R.id.reset_button)).perform(click());
        onView(withId(R.id.reset_button)).perform(click());
        activityRule.getScenario().close();

        //removed it
        tarotPersistence.deleteTarot(testTarot);
        tarotPersistence.insertAll(testList);



    }
}
