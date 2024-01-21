package com.example.trueastrology.persistence;

import com.example.trueastrology.objects.StarSign;
import com.example.trueastrology.objects.User;

import java.util.ArrayList;


public interface IUserPersistence {

    User getUser();

    User updateUser(User updatedUser);

    void deleteUser();

    void insertUser(User toAdd);

}
