package com.example.ksvcem;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Faculty_Profile extends AppCompatActivity {

    private TextView teacher, depart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_profile);
        getSupportActionBar().hide();

        teacher = findViewById(R.id.fac_name);
        depart = findViewById(R.id.dept_name);

    }
}