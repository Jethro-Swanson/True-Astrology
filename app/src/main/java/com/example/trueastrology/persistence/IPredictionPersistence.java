package com.example.trueastrology.persistence;

import java.util.ArrayList;

import com.example.trueastrology.objects.Prediction;

//prediction interactions with db
public interface IPredictionPersistence {
    ArrayList<Prediction> getPredictionList(); //gets all predictions as an array list

    Prediction getPrediction(int ID);

    void addPrediction(Prediction prediction);

    void deletePrediction(int ID);

}
