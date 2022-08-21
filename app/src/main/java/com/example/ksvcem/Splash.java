package com.example.ksvcem;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Splash extends AppCompatActivity {
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();
        new Handler().postDelayed(() -> {
          Intent intent = new Intent(Splash.this , profile.class);
          startActivity(intent);
          finish();
        }, 1000);
    }
}