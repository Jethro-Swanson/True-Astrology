package com.example.trueastrology.logic;

import com.example.trueastrology.app.Services;
import com.example.trueastrology.objects.User;
import com.example.trueastrology.persistence.IUserPersistence;

import java.util.ArrayList;

public class RetrieveUser implements IRetrieveUser{
    private static IUserPersistence persistence;

    public RetrieveUser(IUserPersistence persistence){
        this.persistence = persistence;
    }

    public RetrieveUser(){
        this.persistence = Services.getUserPersistence(true);
    }
    public User getUser(){
        User primary = persistence.getUser();
        return primary;
    }
    public void updateUser(User toUpdate){
        persistence.updateUser(toUpdate);
    }
}
