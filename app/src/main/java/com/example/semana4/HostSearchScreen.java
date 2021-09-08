package com.example.semana4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HostSearchScreen extends AppCompatActivity {

    Button returnBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_search_screen);

        returnBtn = findViewById(R.id.returnButton);

        returnBtn.setOnClickListener(
                (view) ->
                {
                    returnToMainScreen();
                }
        );
    }

    public void returnToMainScreen()
    {
        Intent switchActivity = new Intent(this, MainActivity.class);
        startActivity(switchActivity);
    }
}