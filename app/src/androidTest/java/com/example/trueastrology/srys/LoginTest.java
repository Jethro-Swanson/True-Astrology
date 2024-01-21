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
public class LoginTest {
    @Rule
    public ActivityScenarioRule<LoginPage> activityRule = new ActivityScenarioRule<>(LoginPage.class);

    @Before
    public void setupDatabase(){
        IUserPersistence userPersistence= Services.getUserPersistence(true);
        userPersistence.getUser();

    }

    /*
        To test the LoginPage, We just inserted TestUser as it's name and it should reflect on the homepage.
        We also clear the user's statistics at the end of every test, to make sure the it gets cleared.
     */
    @Test
    public void loginTest(){

        activityRule.getScenario().close();
        ActivityScenario.launch(LoginPage.class);

        onView(withId(R.id.name_text)).perform(click());
        onView(withId(R.id.name_text)).perform(clearText());
        onView(withId(R.id.name_text)).perform(typeText("TestUser"));
        closeSoftKeyboard();
        onView(withId(R.id.submit_button)).perform(click());

        onView(withId(R.id.select_zodiac_TV)).check(matches(withText("Welcome to True Astrology,TestUser")));

        onView(withId(R.id.reset_button)).perform(click());
        onView(withId(R.id.reset_button)).perform(click());

        activityRule.getScenario().close();


    }
}
