package com.example.trueastrology.persistence;

import com.example.trueastrology.objects.Tarot;

import java.util.ArrayList;

public interface ITarotPersistence {

    ArrayList<Tarot> getTarotList();

    Tarot getTarotCard(String cardName);

    void deleteTarot(Tarot toDelete);

    void insertTarot(Tarot toAdd);

    void deleteAll(ArrayList<Tarot> list);

    void insertAll(ArrayList<Tarot> list);
}
