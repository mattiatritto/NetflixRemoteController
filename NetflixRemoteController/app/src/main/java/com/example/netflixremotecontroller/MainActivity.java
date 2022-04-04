package com.example.netflixremotecontroller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity che mostra una spalsh screen all'avvio.
 *
 * @author Mattia Tritto
 */

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME);
    }
}