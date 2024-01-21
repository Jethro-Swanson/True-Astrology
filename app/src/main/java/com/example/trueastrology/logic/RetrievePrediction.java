package com.example.trueastrology.logic;

import com.example.trueastrology.objects.Prediction;
import com.example.trueastrology.app.Services;
import com.example.trueastrology.objects.User;
import com.example.trueastrology.persistence.IPredictionPersistence;

import java.util.ArrayList;

//Retrieve Prediction from the database
public class RetrievePrediction implements IRetrievePrediction{
    private static IPredictionPersistence persistence;

    public RetrievePrediction(IPredictionPersistence persistence){
        this.persistence = persistence;
    }

    public RetrievePrediction(){
        this.persistence = Services.getPredictionPersistence(true);
    }
    //Get a random prediction from prediction list
    static Prediction getRandPred(){
        ArrayList<Prediction> predictionList = persistence.getPredictionList();
        int index = (int)(Math.random() * predictionList.size());
        return predictionList.get(index);
    }

    //helper method to replace prediction chars
    public String getDynamicPred(User user){
        Prediction pred = getRandPred();
        int severity = pred.getSeverity();
        while(severity != user.getPreferredSeverity())
        {
            pred = getRandPred();
            severity = pred.getSeverity();
        }
        return dynamizePrediction(pred, user);
    }

    //helper method to make predictions dynamic
    public static String dynamizePrediction(Prediction pred, User user){
        //replaces the key with the user's star sign
        String predText = pred.getText().replace("$", user.getUserStarSign().getSignName());

        for(int i=0; i< predText.length(); i++){
            //if it finds a # (key used to indicate "a/an")
            if(predText.charAt(i) == '#'){
                String signFirstChar = predText.valueOf(predText.charAt(i+2));
                //if the next word starts with a vowel (theres only A in "aries" and "aquarius")
                if( signFirstChar.equals("A")){
                    predText= predText.replace('#', 'n');
                }
                else{
                    predText= predText.replace("#", "");
                }
            }
        }
        return predText;
    }

}
