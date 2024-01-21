package com.example.trueastrology.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;

import com.example.trueastrology.R;
import com.example.trueastrology.logic.IRetrieveUser;
import com.example.trueastrology.logic.RetrieveUser;
import com.example.trueastrology.objects.User;

import android.view.View;
import android.widget.Button;

import android.content.Intent;
import android.widget.TextView;

//The main home page of the app when it starts up
public class HomePage extends AppCompatActivity {


    private static User user;
    private static IRetrieveUser retrieveUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets layout to be the home page
        setContentView(R.layout.home_page);


        retrieveUser = new RetrieveUser();

        //initializing the stubs
        user = retrieveUser.getUser();
        int backgroundColour = ContextCompat.getColor(getApplicationContext(), R.color.LightCoral);
        if (user.getName().equals("NA")) {
            startActivity(pageNavigation(HomePage.this, LoginPage.class));
        }
        else{
            TextView welcome = (TextView) findViewById(R.id.select_zodiac_TV);
            if (user.getName() != null) {
                welcome.setText("Welcome to True Astrology,"+user.getName());
            }
        }

        user.incrementLogin();
        retrieveUser.updateUser(user);

        //sets up the prediction button to take the user to the prediction results page once clicked
        Button pred_button = findViewById(R.id.prediction_button);
        Button tarot_button = findViewById(R.id.tarot_button);
        Button statistics_button = findViewById(R.id.reset_button);
        pred_button.setOnClickListener(v -> startActivity(pageNavigation(HomePage.this, PredResultPage.class)));
        pred_button.setBackgroundColor(backgroundColour);
        tarot_button.setOnClickListener(v -> startActivity(pageNavigation(HomePage.this, TarotResultPage.class)));
        tarot_button.setBackgroundColor(backgroundColour);
        statistics_button.setBackgroundColor(backgroundColour);
    }


    protected static Intent pageNavigation(Context initial, Class destination){

        Intent intent = null;
        try {
            intent = new Intent(initial,destination);
            if (destination.getName().equals("com.example.trueastrology.ui.HomePage")){
                user.decrementLogin();
                retrieveUser.updateUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return intent;

    }



    public void statsClick(View view) {
        startActivity(pageNavigation(HomePage.this, StatsPage.class));
    }

}
