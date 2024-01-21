package com.example.trueastrology.objects;

//This class is used to describe a prediction which is to be displayed
public class Prediction {
    private String predictionText; //string to store the prediction
    private int ID; //unique ID for the prediction

    private int severity;

    // Prediction constructor
    public Prediction(int ID, String predictionText, int severity) {
        this.ID = ID;
        this.predictionText = predictionText;
        this.severity = severity;
    }

    public int getID() {
        return ID;
    }

    public String getText() {
        return predictionText;
    }

    public int getSeverity(){return severity;}
}
