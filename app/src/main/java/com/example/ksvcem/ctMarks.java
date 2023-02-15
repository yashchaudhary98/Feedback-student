package com.example.ksvcem;

import android.content.Intent;
import android.os.Bundle;

import com.example.ksvcem.databinding.ActivityCtMarksBinding;

public class ctMarks extends drawerBase {

    ActivityCtMarksBinding activityCtMarksBinding;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, profile.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCtMarksBinding = ActivityCtMarksBinding.inflate(getLayoutInflater());
        setContentView(activityCtMarksBinding.getRoot());
        allocateActivityTitle("CT Marks");
//        getSupportActionBar().hide();
    }
}