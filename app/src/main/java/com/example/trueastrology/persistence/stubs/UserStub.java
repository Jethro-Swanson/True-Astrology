package com.example.trueastrology.persistence.stubs;

import com.example.trueastrology.objects.StarSign;
import com.example.trueastrology.objects.User;
import com.example.trueastrology.persistence.IUserPersistence;

import java.util.ArrayList;

public class UserStub implements IUserPersistence {

    private final ArrayList<User> userList;


    //constructor
    public UserStub(){
        this.userList= new ArrayList<>();
        userList.add(new User());
    }

    //accessors
    public User getUser(){
        return userList.get(0);
    }

    public User updateUser(User updatedUser){
        userList.set(0, updatedUser);
        return updatedUser;
    }

    public void deleteUser(){
        if(userList.get(0) != null){
            userList.remove(0);
        }
    }

    public void insertUser(User toAdd){
        if(userList.size() == 0){
            userList.add(toAdd);
        }
    }
}
