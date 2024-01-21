package com.example.trueastrology.persistence;

import com.example.trueastrology.objects.StarSign;

import java.util.ArrayList;

public interface IStarSignPersistence {

    ArrayList<StarSign> getStarSignList();

    StarSign getStarSign(String signName);

    void deleteStarSign(StarSign toDelete);

    void insertStarSign(StarSign toAdd);


}
