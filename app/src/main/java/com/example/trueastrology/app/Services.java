package com.example.trueastrology.app;

import com.example.trueastrology.persistence.IPredictionPersistence;
import com.example.trueastrology.persistence.IStarSignPersistence;
import com.example.trueastrology.persistence.db.StarSignPersistenceDB;
import com.example.trueastrology.persistence.db.PredictionPersistenceDB;
import com.example.trueastrology.persistence.db.TarotPersistenceDB;
import com.example.trueastrology.persistence.db.UserPersistenceDB;
import com.example.trueastrology.persistence.ITarotPersistence;
import com.example.trueastrology.persistence.IUserPersistence;
import com.example.trueastrology.persistence.stubs.PredictionPersistenceStub;
import com.example.trueastrology.persistence.stubs.StarSignStub;
import com.example.trueastrology.persistence.stubs.TarotStub;
import com.example.trueastrology.persistence.stubs.UserStub;

//Get data from database or stub
public class Services {
    private static IPredictionPersistence predictionPersistence = null;
    private static IStarSignPersistence starSignPersistence = null;

    private static IUserPersistence userPersistence = null;

    private static ITarotPersistence tarotPersistence= null;

    //Get PredictionPersistence from database or stub
    public static synchronized IPredictionPersistence getPredictionPersistence(boolean getDB) {
        if (predictionPersistence == null) {
            if(getDB){
                predictionPersistence = new PredictionPersistenceDB(Main.getDBPathName());
            }
            else{
                predictionPersistence = new PredictionPersistenceStub();
            }
        }
        return predictionPersistence;
    }

    //Get StarSignPersistence from database or stub
    public static synchronized IStarSignPersistence getStarSignPersistence(boolean getDB) {
        if (starSignPersistence == null) {
            if(getDB){
                starSignPersistence = new StarSignPersistenceDB(Main.getDBPathName());
            }
            else{
                starSignPersistence = new StarSignStub();
            }
        }
        return starSignPersistence;
    }

    public static IUserPersistence getUserPersistence(boolean getDB){
        if(userPersistence == null){
            if (getDB) {
                userPersistence = new UserPersistenceDB(Main.getDBPathName());
            }
            else{
                userPersistence = new UserStub();
            }
        }
        return userPersistence;
    }

    public static ITarotPersistence getTarotPersistence(boolean getDB){
        if(tarotPersistence == null){
            if (getDB) {
                tarotPersistence = new TarotPersistenceDB(Main.getDBPathName());
            }
            else{
                tarotPersistence = new TarotStub();
            }
        }
        return tarotPersistence;
    }

}
