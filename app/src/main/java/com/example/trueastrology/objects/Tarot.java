package com.example.trueastrology.objects;

import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Tarot {

    //instance variables
    private final int cardNum;
    private final String cardName;
    private String tarotText;

    //private final ImageView imageView;

    //constructor
    public Tarot(int cardNum, String cardName, String tarotText){
        this.cardNum= cardNum;
        this.tarotText = tarotText;
        this.cardName= cardName;
        //this.imageView=null;
    }

    //accessors
    public int getCardNum(){return cardNum;}

    public String getTarotText(){return tarotText;}

    public String getCardName(){return cardName;}


    //mutators
    public void setTarotText(String s){
        this.tarotText = s;
    }
}
