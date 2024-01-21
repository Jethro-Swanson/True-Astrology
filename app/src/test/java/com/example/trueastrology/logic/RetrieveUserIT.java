package com.example.trueastrology.logic;
import static org.junit.Assert.*;

import com.example.trueastrology.Utilities.testUtilities;
import com.example.trueastrology.objects.User;
import com.example.trueastrology.persistence.db.PredictionPersistenceDB;
import com.example.trueastrology.persistence.db.UserPersistenceDB;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class RetrieveUserIT {
    private IRetrieveUser retrieveUser;
    private File tempDB;

    @Before
    public void onStartUp() throws IOException{
        try {
            this.tempDB = testUtilities.makeDbCopy();
            final UserPersistenceDB persistenceDB = new UserPersistenceDB(this.tempDB.getAbsolutePath().replace(".script", ""));
            this.retrieveUser = new RetrieveUser(persistenceDB);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetUser(){
        System.out.println("Beginning testGetUser: ");
        assertNotNull(retrieveUser.getUser());
        System.out.println("Ending testGetUser");
    }

    @Test
    public void testUpdateUser(){
        System.out.println("Beginning testUpdateUser:");
        User testUser = retrieveUser.getUser();
        testUser.setName("TestName");
        retrieveUser.updateUser(testUser);
        assertEquals(retrieveUser.getUser().getName(), "TestName" );
        System.out.println("Ending testUpdateUser");
    }

    @After
    public void uponFinishing(){this.tempDB.delete();}

}
