package com.example.trueastrology.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trueastrology.R;
import com.example.trueastrology.logic.IRetrieveUser;
import com.example.trueastrology.logic.RetrieveUser;
import com.example.trueastrology.objects.User;

public class StatsPage extends AppCompatActivity {

    TextView header;
    TextView name;
    TextView starSign;
    TextView logins;
    TextView predictions;

    IRetrieveUser retrieveUser;

    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_page);

        header= (TextView) findViewById(R.id.header);
        name = (TextView) findViewById(R.id.user_name);
        starSign = (TextView) findViewById(R.id.user_starsign);
        logins = (TextView) findViewById(R.id.number_logins);
        predictions = (TextView) findViewById(R.id.number_prediction);
        Button resetButton = findViewById(R.id.reset_button);
        int backgroundColour = ContextCompat.getColor(getApplicationContext(), R.color.LightCoral);
        resetButton.setBackgroundColor(backgroundColour);
        Button nextButtton = findViewById(R.id.back);
        nextButtton.setBackgroundColor(backgroundColour);
        retrieveUser = new RetrieveUser();
        user = retrieveUser.getUser();

        header.setText("User Statistics");
        name.setText("Name: " + user.getName());
        starSign.setText("StarSign: " + user.getUserStarSign().getSignName());
        logins.setText("Number of Logins: " + user.getLogin());
        predictions.setText("Number of predictions requested: " + user.getPredictionCounter());
    }

    public void backClick(View view) {
        startActivity(HomePage.pageNavigation(StatsPage.this, HomePage.class));
    }

    public void resetUser(View view){
        user.resetUser();
        retrieveUser.updateUser(user);
        startActivity(HomePage.pageNavigation(StatsPage.this, LoginPage.class));
    }
}