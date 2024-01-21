package com.example.trueastrology.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.trueastrology.R;
import com.example.trueastrology.logic.IRetrieveTarot;
import com.example.trueastrology.logic.IRetrieveUser;
import com.example.trueastrology.logic.RetrieveTarot;
import com.example.trueastrology.logic.RetrieveUser;
import com.example.trueastrology.objects.Tarot;

public class TarotResultPage extends AppCompatActivity {
    TextView prediction;

    TextView cardName;

    IRetrieveTarot retrieveTarot;

    IRetrieveUser retrieveUser;
    int[] backgroundImages;

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tarot_result_page);
        retrieveTarot = new RetrieveTarot();
        retrieveUser = new RetrieveUser();

        prediction = (TextView) findViewById(R.id.TarotPredictionText);
        Tarot card = retrieveTarot.getDynamicTarot(retrieveUser.getUser());

        prediction.setText(card.getTarotText());

        cardName = (TextView)findViewById(R.id.tarot);
        String title = "#"+ Integer.toString(card.getCardNum()) + ":\t" + card.getCardName();
        cardName.setText(title);

        backgroundImages=new int[]{R.drawable.thefool,R.drawable.themagician,R.drawable.thehighpreistess,R.drawable.theempress,R.drawable.theemperor,
                                   R.drawable.theheirophant,R.drawable.thelovers,R.drawable.thechariot,R.drawable.thestrength,R.drawable.thehermit,
                                   R.drawable.thewheeloffortune,R.drawable.thejustice,R.drawable.thehangedman,R.drawable.thedeath,R.drawable.thetemperance,
                                   R.drawable.thedevil,R.drawable.thetower,R.drawable.thestar,R.drawable.themoon,R.drawable.thesun,R.drawable.thejudgement,
                                   R.drawable.theworld,R.drawable.theworld};
        Button backButton = findViewById(R.id.back);
        int backgroundColour = ContextCompat.getColor(getApplicationContext(), R.color.LightCoral);
        backButton.setBackgroundColor(backgroundColour);
        imageView=findViewById(R.id.tarotImage);
        imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),backgroundImages[card.getCardNum()]));
    }

    public void backClick(View view) {
        startActivity(HomePage.pageNavigation(TarotResultPage.this, HomePage.class));
    }
}