package com.example.trueastrology.objects;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

import com.example.trueastrology.R;

public class StarSign {
    //instance variable
    private String signName;
    private int starID;
    //constructor
    public StarSign(String signName){
        this.signName= signName;
        setStarID();
    }

    private void setStarID(){
        String check=this.signName;
        switch (check){
            case "Aries" : this.starID=0;
                break;
            case "Taurus" : this.starID=1;
                break;
            case "Gemini" : this.starID=2;
                break;
            case "Cancer" : this.starID=3;
                break;
            case "Leo" : this.starID=4;
                break;
            case "Virgo" : this.starID=5;
                break;
            case "Libra" : this.starID=6;
                break;
            case "Scorpio" : this.starID=7;
                break;
            case "Sagittarius" : this.starID=8;
                break;
            case "Capricorn" : this.starID=9;
                break;
            case "Aquarius" : this.starID=10;
                break;
            case "Pisces" : this.starID=11;
                break;
            default: this.starID=0;
                break;
        }
    }
    public StarSign(String signName,int starID){
        this.signName=signName;
        this.starID=starID;
    }
    //getter
    public String getSignName(){ return signName;}
    public int getStarID(){return starID;}
    //helper method: checks whether a particular star sign is the one we require based on the string
    public boolean isEquals(String s){
        return this.signName.equals(s);
    }


}
