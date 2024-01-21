package com.example.trueastrology.logic;

import com.example.trueastrology.objects.StarSign;
import com.example.trueastrology.app.Services;
import com.example.trueastrology.persistence.IStarSignPersistence;

import java.util.ArrayList;

//Retrieve StarSign from database
public class RetrieveStarSign implements IRetrieveStarSign {
    private static IStarSignPersistence persistence;

    public RetrieveStarSign(IStarSignPersistence persistence){
        this.persistence = persistence;
    }

    public RetrieveStarSign(){
        this.persistence = Services.getStarSignPersistence(true);
    }

    //Taking a star sign name and return a StarSign object
    public StarSign getStarSign(String signName){
        ArrayList<StarSign> starSignList = persistence.getStarSignList();
        StarSign retrieved= null;
        for(int i=0; i< starSignList.size(); i++){
            if(starSignList.get(i).isEquals(signName)){
                retrieved= starSignList.get(i);
            }
        }
        return retrieved;
    }

    public String[] getStarSignStringList(){
        ArrayList<StarSign> starSignList = persistence.getStarSignList();
        String[] starSignStrings = new String[starSignList.size()];
        for (int i =0; i< starSignList.size(); i++ ){
            starSignStrings[i] = starSignList.get(i).getSignName();
        }
        return starSignStrings;
    }
}
