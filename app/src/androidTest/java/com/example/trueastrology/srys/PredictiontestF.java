package com.example.trueastrology.srys;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.trueastrology.R;
import com.example.trueastrology.app.Services;
import com.example.trueastrology.objects.Prediction;
import com.example.trueastrology.persistence.IPredictionPersistence;
import com.example.trueastrology.persistence.IUserPersistence;
import com.example.trueastrology.ui.LoginPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PredictiontestF {
    @Rule
    public ActivityScenarioRule<LoginPage> activityRule = new ActivityScenarioRule<>(LoginPage.class);

    IUserPersistence userPersistence;
    @Before
    public void setupDatabase(){
        userPersistence = Services.getUserPersistence(true);
        userPersistence.getUser().resetUser();
    }

    /*
        To test the predictions :
        We checked whether milder,harsh  has changes to the user's preferred severity.
        Also gave a dummy prediction with a wrong severity, to make sure the predcitions dispalyed mathes the
        user's preference as indicated in the success criteria
     */
    @Test
    public void predictionTest(){
        IPredictionPersistence predictionPersistence = Services.getPredictionPersistence(true);

        //adding the dummy predictions
        Prediction prediction = new Prediction(99,"This is a test",10);
        Prediction wrongPrediction = new Prediction(101, "This is test for wrong prediction severity",7);

        predictionPersistence.addPrediction(prediction);
        predictionPersistence.addPrediction(wrongPrediction);

        activityRule.getScenario().close();
        ActivityScenario.launch(LoginPage.class);

        onView(withId(R.id.name_text)).perform(click());
        onView(withId(R.id.name_text)).perform(clearText());
        onView(withId(R.id.name_text)).perform(typeText("TestUser"));
        closeSoftKeyboard();
        onView(withId(R.id.submit_button)).perform(click());

        //checking if intially user's preference is 3
        assertEquals(Services.getUserPersistence(true).getUser().getPreferredSeverity(),3);
        assertNotEquals(Services.getUserPersistence(true).getUser().getPreferredSeverity(),predictionPersistence.getPrediction(101).getSeverity());

        //to make the predictions milder
        onView(withId(R.id.prediction_button)).perform(click());
        onView(withId(R.id.milder)).perform(click());
        assertEquals(Services.getUserPersistence(true).getUser().getPreferredSeverity(),2);

        //for harsher predictions
        onView(withId(R.id.prediction_button)).perform(click());
        onView(withId(R.id.harsher)).perform(click());
        assertEquals(Services.getUserPersistence(true).getUser().getPreferredSeverity(),3);

        //clearing out the statistics
        onView(withId(R.id.reset_button)).perform(click());
        onView(withId(R.id.reset_button)).perform(click());

        activityRule.getScenario().close();

        //deleting the predictions
        predictionPersistence.deletePrediction(99);
        predictionPersistence.deletePrediction(101);

    }

}
