package com.example.trueastrology.logic;

import com.example.trueastrology.objects.User;

public interface IRetrieveUser {
    User getUser();
    void updateUser(User toUpdate);
}
