package com.example.ksvcem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


@SuppressWarnings("ALL")
public class profile extends AppCompatActivity {

    private ImageView feedback, res, data, chat;
    private TextView name, sem, branchStud;
    private ImageView profile, fac, ct;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        drawerLayout = findViewById(R.id.side_nav);
        navigationView = findViewById(R.id.navigation_side_nav);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();




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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.homenav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homenav:
                        return true;
                    case R.id.ProfileNav:
                        Intent intent = new Intent(getApplicationContext(), profile_page.class);
                        intent.putExtra("name", name.getText().toString());
                        intent.putExtra("branch", branchStud.getText().toString());
                        intent.putExtra("semester", sem.getText().toString());
                        startActivityForResult(intent, 1);
                        return true;

                    case R.id.webnav:
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse("https://ksvira.edu.in/vira_home.php"));
                        startActivity(i);
                        return true;

                    case R.id.LinkedInNav:
                        Intent j = new Intent(Intent.ACTION_VIEW);
                        j.setData(Uri.parse("https://www.linkedin.com/school/kunwar-satyavira-college-of-engineering-and-management-bijnor./about/"));
                        startActivity(j);
                        return true;

                    case R.id.TweetNav:
                        Intent k = new Intent(Intent.ACTION_VIEW);
                        k.setData(Uri.parse("https://twitter.com/ksvcem"));
                        startActivity(k);
                        return true;

                }
                return false;
            }
        });

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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==1){
//
//            if(resultCode== Activity.RESULT_OK){
//
//            }
//
//        }
//    }
}