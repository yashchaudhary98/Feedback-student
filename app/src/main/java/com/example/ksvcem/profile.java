package com.example.ksvcem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.ksvcem.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


@SuppressWarnings("ALL")
public class profile extends drawerBase{

    ActivityProfileBinding activityProfileBinding;

    private ImageView feedback, res, data, chat;
    private TextView name, sem, branchStud;
    private ImageView profile, fac, ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProfileBinding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(activityProfileBinding.getRoot());

        allocateActivityTitle("Home");


        profile = findViewById(R.id.profile);
        feedback = findViewById(R.id.feedback);
        name = findViewById(R.id.username);
        sem = findViewById(R.id.sem);
        branchStud = findViewById(R.id.branch);
        res = findViewById(R.id.report);
        data = findViewById(R.id.syllabus);
        chat = findViewById(R.id.complain);
        fac = findViewById(R.id.faculty);
        ct = findViewById(R.id.ctMarks);

        Glide.with(this)
                .load(R.drawable.graduated)
                .into(profile);

        Glide.with(this)
                .load(R.drawable.queries)
                .into(chat);

        Glide.with(this)
                .load(R.drawable.book)
                .into(data);

        Glide.with(this)
                .load(R.drawable.results)
                .into(res);

        Glide.with(this)
                .load(R.drawable.teacher)
                .into(fac);

        Glide.with(this)
                .load(R.drawable.review)
                .into(feedback);

        Glide.with(this)
                .load(R.drawable.ctmarks)
                .into(ct);



        profile.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), profile_page.class);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("branch", branchStud.getText().toString());
                intent.putExtra("semester", sem.getText().toString());
                startActivityForResult(intent, 1);
            }
        });



        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, contact.class);
                startActivity(intent);
            }
        });

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, curriculum.class);
                startActivity(intent);
            }
        });

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, result.class);
                startActivity(intent);
            }
        });

        fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, Faculty_Profile.class);
                startActivity(intent);
            }
        });

        ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, ctMarks.class);
                startActivity(intent);
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        assert user != null;
        String userID = user.getUid();


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, feedback_Input.class);
                startActivity(intent);
            }
        });


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user userProfile = snapshot.getValue(user.class);
                if (userProfile != null) {
                    String fullName = userProfile.fullName;
                    String branch = userProfile.branch;
                    String year = userProfile.year;

                    name.setText(fullName);
                    sem.setText(" ( "+ year +" ) ");
                    branchStud.setText(branch);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(profile.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        });




    }

}