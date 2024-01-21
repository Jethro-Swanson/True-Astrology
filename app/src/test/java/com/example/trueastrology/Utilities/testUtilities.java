package com.example.trueastrology.Utilities;

import com.example.trueastrology.app.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class testUtilities {
    private static final File SOURCE_DB= new File("src/main/assets/db/ASTROLOGY_DB_FINAL.script");

    public static File makeDbCopy() throws IOException{
        final File targetDB = File.createTempFile("testDB", ".script");
        Files.copy(SOURCE_DB.toPath(), targetDB.toPath(), StandardCopyOption.REPLACE_EXISTING);
        Main.setDBPathName(targetDB.getAbsolutePath().replace(".script", ""));
        return targetDB;
    }
}
