package com.example.trueastrology.logic;

import com.example.trueastrology.objects.StarSign;

public interface IRetrieveStarSign {

    StarSign getStarSign(String signName);

    String[] getStarSignStringList();
}
