package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Splash Screen as a welcome page that has duration of only 1 second
 */
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        // Create a handler object to create a splash screen
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create new intent object(anonymous object)
                startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
                finish();
            }
        }, 1000);
    }
}