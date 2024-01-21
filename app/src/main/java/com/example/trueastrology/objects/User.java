package com.example.trueastrology.objects;

import com.example.trueastrology.persistence.stubs.StarSignStub;

import java.util.ArrayList;

public class User {

    private int userID ; //id to identify each user

    private String name;
    private StarSign userStarSign; //star-sign of the user


    private int loginCounter; //to store the amount of logins
    private int predictionCounter;

    private int preferredSeverity;

    private StarSignStub signStub = new StarSignStub();

    //constructor
    public User(){
        userStarSign=signStub.getStarSignList().get(0); //assigns the first sign in the list
        name = "NA";
        userID=0;
        loginCounter=0;
        predictionCounter=0;
        preferredSeverity = 3;
    }

    public User(
            StarSign userStarSign,
            int userID,
            String name){
        this.userStarSign=userStarSign;
        this.name = name;
        this.userID=userID;
        this.loginCounter= 0;
        this.predictionCounter= 0;
        this.preferredSeverity = 3;
    }

    public User(
            StarSign userStarSign,
            int userID,
            String name,
            int loginCounter,
            int predictionCounter){
        this.userStarSign=userStarSign;
        this.userID=userID;
        this.name = name;
        this.loginCounter= loginCounter;
        this.predictionCounter= predictionCounter;
        this.preferredSeverity = 3;
    }


    //accessor methods
    public StarSign getUserStarSign(){ //to get the User-starsign
        return this.userStarSign;
    }

    public int getUserID(){ //to get the userId
        return this.userID;
    }

    public String getName(){return name;}


    public int getLogin(){return loginCounter;}
    public int getPredictionCounter(){return predictionCounter;}

    public int getPreferredSeverity(){return preferredSeverity;}


    //mutators
    public void setUserStarSign(StarSign userStarSign){
        this.userStarSign = userStarSign;
    }

    public void incrementLogin(){loginCounter++;}
    public void incrementPred(){predictionCounter++;}

    public void decrementLogin(){loginCounter--;}
    public void decrementPred(){predictionCounter--;}

    public void setName(String name){this.name= name;}

    public void setUserID(int newID){this.userID = newID;}
    public void resetLogin(){loginCounter = 0;}
    public void resetPredictionCounter(){predictionCounter = 0;}

    public void setPreferredSeverity(int preferredSeverity){this.preferredSeverity = preferredSeverity;}

    public boolean incrementSeverity(){
        boolean toReturn = false;
        if(preferredSeverity < 5 ){
            this.preferredSeverity++;
            toReturn = true;
        }
        return toReturn;
    }

    public boolean decrementSeverity(){
        boolean toReturn = false;
        if(preferredSeverity > 1 ){
            this.preferredSeverity--;
            toReturn = true;
        }
        return toReturn;
    }

    public void resetUser(){
        this.resetLogin();
        this.resetPredictionCounter();
        this.setName("NA");
        this.setUserStarSign(signStub.getStarSignList().get(0));
        this.setPreferredSeverity(3);
        this.setUserID(0);
    }
}
