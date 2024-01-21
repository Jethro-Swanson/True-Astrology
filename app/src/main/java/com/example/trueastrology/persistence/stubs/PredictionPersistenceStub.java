package com.example.trueastrology.persistence.stubs;

import com.example.trueastrology.persistence.IPredictionPersistence;

import com.example.trueastrology.objects.Prediction;

import java.util.ArrayList;

//implements a stub database for predictions
public class PredictionPersistenceStub implements IPredictionPersistence {
    private final ArrayList<Prediction> predictionList;//db stub

    //constructor
    public PredictionPersistenceStub() {
        this.predictionList = new ArrayList<>();

        predictionList.add(new Prediction(
                0,
                "Given you're a# $, you will receive concerning news in the near future", 1
        ));
        predictionList.add(new Prediction(1,
                "Rejoice, $! Your day will be filled with success, motivation and foot cramps.", 2));

        predictionList.add(new Prediction(2,
                "Congratulations! You’re the lucky $ who will get hit by a bus today!", 4));

        predictionList.add(new Prediction(3,
                "As a# $, today marks the start of a new journey for you. A new life. A life with an STD.", 3));

        predictionList.add(new Prediction(4,
                "Being $ makes you special. A certain fire flows through you. A never-ending flame that you’ve always felt coursing through your chest. " +
                        "It’s acid reflux. Take an antacid.", 2));

        predictionList.add(new Prediction(5, "Based on the current alignment of stars, as a# $, today will be relatively bland for you.", 1));

        predictionList.add(new Prediction(6,
                "What an amazing day for a# $ today! Your day will be filled with happiness and optimism! " +
                        "You’ll feel like you’re on top of the world the whole day! And just before you go to bed, you’ll stub your pinky toe.", 2));
        predictionList.add(new Prediction(7,
                "An eventful day for a# $. You’ll meet a Cancer and spend the rest of your life together! It’s inoperable and terminal. We’re sorry.", 5));

        predictionList.add(new Prediction(8,
                "\"Sorry I can't help it I am a# $\" can get you out of some situations, " +
                        "but it won't save you from your upcoming life sentence.", 3));

        predictionList.add(new Prediction(9,
                "With Mercury in retrograde, it might be time for you to find your freedom, $. " +
                        "The stars have recommended you to go off the grid, delete social media, abandon your family," +
                        " and live a remote life... for your own good.", 3));

        predictionList.add(new Prediction(10,
                "Might be time for a career change, $. You clearly have no clue what you are doing right now.", 2));

        predictionList.add(new Prediction(11,
                "We don't give free predictions to your kind, $. Pay a kidney or look elsewhere.", 4));
    }

    @Override
    public ArrayList<Prediction> getPredictionList() {
        return predictionList;
    }

    @Override
    public Prediction getPrediction(int ID){
        for(int i=0; i<predictionList.size(); i++){
            if(i == ID){
                return predictionList.get(i);
            }
        }
        return null;
    }

    @Override
    public void addPrediction(Prediction prediction){
        predictionList.add(prediction);
    }

    @Override
    public void deletePrediction(int ID){
        for(int i=0; i<predictionList.size(); i++){
            if(i == ID){
                predictionList.remove(i);
            }
        }
    }

}

