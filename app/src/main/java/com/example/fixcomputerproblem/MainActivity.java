package com.example.fixcomputerproblem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homescren=new Intent(getApplicationContext(), WelcomeScreen.class);
                startActivity(homescren);
            }
        },SPLASH_DISPLAY_LENGTH);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}