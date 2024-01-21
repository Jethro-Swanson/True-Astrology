package com.example.trueastrology.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trueastrology.R;
import com.example.trueastrology.logic.IRetrievePrediction;
import com.example.trueastrology.logic.IRetrieveUser;
import com.example.trueastrology.logic.RetrievePrediction;
import com.example.trueastrology.logic.RetrieveUser;
import com.example.trueastrology.objects.User;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.TimeUnit;


//The results page for horoscope predictions
public class PredResultPage extends AppCompatActivity{
    private static User user;
    TextView textView;

    IRetrieveUser retrieveUser;
    IRetrievePrediction retrievePrediction;

    ImageView predImage;
    int[] backImages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets layout to be the result page for predictions
        setContentView(R.layout.pred_result_page);
        retrieveUser = new RetrieveUser();

        user = retrieveUser.getUser();
        user.incrementPred(); //increasing prediction counter

        textView = (TextView) findViewById(R.id.PredictionText);
        retrievePrediction = new RetrievePrediction();
        textView.setText(retrievePrediction.getDynamicPred(retrieveUser.getUser()));

        backImages=new int[]{R.drawable.aries,R.drawable.taurus,R.drawable.gemini,R.drawable.cancer,
                R.drawable.leo,R.drawable.virgo,R.drawable.libra,R.drawable.scorpio,R.drawable.sagittarius,
                R.drawable.capricorn,R.drawable.aquarius,R.drawable.pisces};

        predImage= findViewById(R.id.predictionImage);
        predImage.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),backImages[retrieveUser.getUser().getUserStarSign().getStarID()]));
    }

    public void backClick(View view) {
        user.decrementLogin();
        retrieveUser.updateUser(user);
        Intent intent = new Intent(PredResultPage.this, HomePage.class);
        startActivity(intent);
    }

    public void milderButton(View view) {
        if(!user.decrementSeverity()){
            Toast toast = Toast.makeText(this, "This is as mild as it gets. Learn to take a joke.", Toast.LENGTH_LONG);
            toast.show();
        }
        backClick(view);
    }

    public void harsherButton(View view){
        if(!user.incrementSeverity()){
            Toast toast = Toast.makeText(this, "Okay, even THAT wasn't enough for you? Seek professional help.", Toast.LENGTH_LONG);
            toast.show();
        }
        backClick(view);
    }
}