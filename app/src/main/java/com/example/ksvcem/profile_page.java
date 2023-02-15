package com.example.ksvcem;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile_page extends AppCompatActivity {


    private TextView name,year,branch;
    private TextView email, number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        name = findViewById(R.id.username);
        year = findViewById(R.id.sem);
        branch = findViewById(R.id.branch);

        String naam = getIntent().getStringExtra("name");
        String saal = getIntent().getStringExtra("semester");
        String branching = getIntent().getStringExtra("branch");

        name.setText(naam);
        year.setText(saal);
        branch.setText(branching);

        email = findViewById(R.id.mail);
        number = findViewById(R.id.no);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        assert user != null;
        String userID = user.getUid();


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user userProfile = snapshot.getValue(user.class);
                if (userProfile != null) {
                    String letter = userProfile.email;
                    String num = userProfile.phone;

                    email.setText(letter);
                    number.setText(num);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(profile_page.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        });


    }
}