package com.example.trueastrology.srys;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.trueastrology.R;
import com.example.trueastrology.app.Services;
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

@RunWith(AndroidJUnit4.class)
@LargeTest
public class StatisticsTest {
    @Rule

    public ActivityScenarioRule<LoginPage> activityRule = new ActivityScenarioRule<>(LoginPage.class);

    @Before
    public void setupDatabase(){
        IUserPersistence userPersistence = Services.getUserPersistence(true);
        userPersistence.getUser().resetUser();
    }

    /*
        To check the statistics page
        checked the statistics before any predictions generated
        checked the page, after generating them
        also, close the application and login in again to test it
     */
    @Test
    public void statisticsTest(){
        activityRule.getScenario().close();
        ActivityScenario.launch(LoginPage.class);

        onView(withId(R.id.name_text)).perform(click());
        onView(withId(R.id.name_text)).perform(clearText());
        onView(withId(R.id.name_text)).perform(typeText("TestUser"));
        closeSoftKeyboard();
        onView(withId(R.id.submit_button)).perform(click());

        onView(withId(R.id.reset_button)).perform(click());

        onView(withId(R.id.user_name)).check(matches(withText("Name: TestUser")));
        onView(withId(R.id.user_starsign)).check(matches(withText("StarSign: Aries")));
        onView(withId(R.id.number_logins)).check(matches(withText("Number of Logins: 1")));
        onView(withId(R.id.number_prediction)).check(matches(withText("Number of predictions requested: 0")));

        onView(withId(R.id.back)).perform(click());

        onView(withId(R.id.prediction_button)).perform(click());
        onView(withId(R.id.milder)).perform(click());
        onView(withId(R.id.reset_button)).perform(click());
        onView(withId(R.id.number_prediction)).check(matches(withText("Number of predictions requested: 1")));

        activityRule.getScenario().close();

        ActivityScenario.launch(LoginPage.class);

        onView(withId(R.id.submit_button)).perform(click());
        onView(withId(R.id.reset_button)).perform(click());
        onView(withId(R.id.number_logins)).check(matches(withText("Number of Logins: 2")));

        onView(withId(R.id.reset_button)).perform(click());
        activityRule.getScenario().close();

    }
}
