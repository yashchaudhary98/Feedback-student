package com.example.ksvcem;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ksvcem.databinding.ActivityFacultyProfileBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Faculty_Profile extends drawerBase {

    ActivityFacultyProfileBinding activityFacultyProfileBinding;

    RecyclerView recyclerView;
    myAdapter adapter;


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
        activityFacultyProfileBinding = ActivityFacultyProfileBinding.inflate(getLayoutInflater());
        setContentView(activityFacultyProfileBinding.getRoot());
//        getSupportActionBar().hide();
        allocateActivityTitle("Faculty");
        recyclerView = findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<modal_class> options =
                new FirebaseRecyclerOptions.Builder<modal_class>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Faculty"), modal_class.class)
                        .build();

        adapter = new myAdapter(options);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}