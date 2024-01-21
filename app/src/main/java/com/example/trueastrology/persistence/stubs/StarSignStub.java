package com.example.trueastrology.persistence.stubs;

import com.example.trueastrology.objects.StarSign;
import com.example.trueastrology.persistence.IStarSignPersistence;

import java.util.ArrayList;

public class StarSignStub implements IStarSignPersistence {

    //instance variable
    private final ArrayList<StarSign> starSignList;

    //constructor
    public StarSignStub(){
        this.starSignList = new ArrayList<>();

        starSignList.add(new StarSign("Aries"));
        starSignList.add(new StarSign("Taurus"));
        starSignList.add(new StarSign("Gemini"));
        starSignList.add(new StarSign("Cancer"));
        starSignList.add(new StarSign("Leo"));
        starSignList.add(new StarSign("Virgo"));
        starSignList.add(new StarSign("Libra"));
        starSignList.add(new StarSign("Scorpio"));
        starSignList.add(new StarSign("Sagittarius"));
        starSignList.add(new StarSign("Capricorn"));
        starSignList.add(new StarSign("Aquarius"));
        starSignList.add(new StarSign("Pisces"));
    }

    //getters
    public ArrayList<StarSign> getStarSignList(){
        return starSignList;
    }

    //helper method: returns a StarSign object by traversing through the list and finding the required
    //star sign
    public StarSign getStarSign(String signName){
        StarSign retrieved= null;
        for(int i=0; i< starSignList.size(); i++){
            if(starSignList.get(i).isEquals(signName)){
                retrieved= starSignList.get(i);
            }
        }
        return retrieved;
    }

    public String[] getStarSignString(){
        ArrayList<StarSign> starSignList = this.getStarSignList();
        String[] starSignStrings = new String[starSignList.size()];
        for (int i =0; i< starSignList.size(); i++ ){
            starSignStrings[i] = starSignList.get(i).getSignName();
        }
        return starSignStrings;
    }

    public void deleteStarSign(StarSign toDelete){
        for(int i= 0; i< starSignList.size(); i++){
            if(starSignList.get(i).getStarID() == toDelete.getStarID()){
                starSignList.remove(i);
            }
        }
    }

    public void insertStarSign(StarSign toAdd){
        if(!starSignList.contains(toAdd)){
            starSignList.add(toAdd);
        }
    }
}


