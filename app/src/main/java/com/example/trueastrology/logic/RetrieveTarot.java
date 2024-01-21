package com.example.trueastrology.logic;

import com.example.trueastrology.app.Services;
import com.example.trueastrology.objects.Prediction;
import com.example.trueastrology.objects.Tarot;
import com.example.trueastrology.objects.User;
import com.example.trueastrology.persistence.ITarotPersistence;

import java.util.ArrayList;

public class RetrieveTarot implements IRetrieveTarot{
    private static ITarotPersistence persistence;

    public RetrieveTarot(){
        this.persistence = Services.getTarotPersistence(true);
    }

    public RetrieveTarot(ITarotPersistence persistence){
        this.persistence = persistence;
    }
    public static Tarot getRandTarot(){
        ArrayList<Tarot> tarotList = persistence.getTarotList();
        int index= (int)(Math.random() * tarotList.size());
        return tarotList.get(index);
    }

    public static Tarot getTarotCard(String cardName){
        ArrayList<Tarot> tarotList= persistence.getTarotList();
        Tarot retrieved = null;
        for(int i=0; i< tarotList.size(); i++){
            if(tarotList.get(i).getCardName().equals(cardName)){
                retrieved = tarotList.get(i);
            }
        }
        return retrieved;
    }

    public static String[] getTarotStringList(){
        ArrayList<Tarot> tarotList= persistence.getTarotList();
        String[] tarotListString = new String[tarotList.size()];
        for(int i=0; i< tarotList.size(); i++){
            tarotListString[i]= tarotList.get(i).getCardName();
        }
        return tarotListString;
    }

    public Tarot getDynamicTarot(User user){
        Tarot tarot = getRandTarot();
        tarot.setTarotText(tarot.getTarotText().replace("$", user.getName()));
        return tarot;
    }



}
