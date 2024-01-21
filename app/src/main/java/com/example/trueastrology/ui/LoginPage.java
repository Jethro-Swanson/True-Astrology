package com.example.trueastrology.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trueastrology.R;
import com.example.trueastrology.app.Main;
import com.example.trueastrology.logic.IRetrieveStarSign;
import com.example.trueastrology.logic.IRetrieveUser;
import com.example.trueastrology.logic.RetrievePrediction;
import com.example.trueastrology.logic.RetrieveStarSign;
import com.example.trueastrology.logic.RetrieveTarot;
import com.example.trueastrology.logic.RetrieveUser;
import com.example.trueastrology.objects.StarSign;
import com.example.trueastrology.objects.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginPage extends AppCompatActivity{

    private IRetrieveStarSign retrieveStarSign;

    IRetrieveUser retrieveUser;
    private static User user;

    private Spinner dropdown ;

    private TextView error;
    private EditText editText;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets layout to be the home page
        setContentView(R.layout.activity_login_page);

        copyDatabaseToDevice();
        new RetrieveTarot();
        new RetrievePrediction();
        dropdown = (Spinner) findViewById(R.id.dropdown_menu);
        error = (TextView) findViewById(R.id.error_text);
        editText = (EditText) findViewById(R.id.name_text);
        retrieveStarSign = new RetrieveStarSign();
        Button login  = findViewById(R.id.submit_button);
        int backgroundColour = ContextCompat.getColor(getApplicationContext(), R.color.LightCoral);
        login.setBackgroundColor(backgroundColour);
        String[] starSignStrings = retrieveStarSign.getStarSignStringList();
        retrieveUser = new RetrieveUser();
        user = retrieveUser.getUser();
        if(!user.getName().equals("NA")){
            editText.setText(user.getName());
        }
        // sets up drop down menu
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, starSignStrings);
        int position = adapter.getPosition(user.getUserStarSign().getSignName());
        dropdown.setAdapter(adapter);
        dropdown.setSelection(position);

    }

    public void loginButton(View view) {
        if (!editText.getText().toString().trim().isEmpty()) {
            user.setName(editText.getText().toString());
            StarSign sign = retrieveStarSign.getStarSign(dropdown.getSelectedItem().toString());
            //retrieving the user from the stub and assigning the star sign to them
            user.setUserStarSign(sign);
            retrieveUser.updateUser(user);
            Intent next = new Intent(LoginPage.this, HomePage.class);
            startActivity(next);
        }
        else{
            error.setText("PLEASE INSERT A NAME");
        }
    }

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);
            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch (final IOException ioe) {
            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}