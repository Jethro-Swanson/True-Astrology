package com.example.trueastrology.logic;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.trueastrology.app.Services;
import com.example.trueastrology.persistence.IUserPersistence;

public class RetrieveUserTest {
    @Test
    public void testRetrieveUser(){
        System.out.println("Beginning RetrieveUserTest");
        IUserPersistence userPersistence = Services.getUserPersistence(false);
        RetrieveUser retrieveUser = new RetrieveUser(userPersistence);
        assertNotNull(retrieveUser.getUser());

        System.out.println("Completed RetrieveUserTest");
    }

}
