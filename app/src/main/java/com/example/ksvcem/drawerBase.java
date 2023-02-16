package com.example.ksvcem;

import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class drawerBase extends AppCompatActivity {

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        // Call the garbage collector to release memory from the native heap
        Runtime.getRuntime().gc();
    }


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    public void setContentView(View view) {

        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base, null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);
        navigationView = drawerLayout.findViewById(R.id.navigation_side_nav);
        toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.invisible);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homenav:
                        Intent intent = new Intent(getApplicationContext(), profile.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                        break;

                    case R.id.webnav:
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse("https://ksvira.edu.in/vira_home.php"));
                        startActivity(i);
                        break;

                    case R.id.LinkedInNav:
                        Intent j = new Intent(Intent.ACTION_VIEW);
                        j.setData(Uri.parse("https://www.linkedin.com/school/kunwar-satyavira-college-of-engineering-and-management-bijnor./about/"));
                        startActivity(j);
                        break;

                    case R.id.TweetNav:
                        Intent k = new Intent(Intent.ACTION_VIEW);
                        k.setData(Uri.parse("https://twitter.com/ksvcem"));
                        startActivity(k);
                        break;

                }
                return false;
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                drawerLayout.closeDrawer(GravityCompat.START);
                switch (item.getItemId()){
                    case R.id.Home_side_nav:
                        Intent intent = new Intent(getApplicationContext(), profile.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                        break;
                    case R.id.Profile_side_nav:
                        startActivity(new Intent(getApplicationContext(), profile_page.class));
                        break;
                    case R.id.Privacy_policy_side_nav:
                        startActivity(new Intent(getApplicationContext(), PrivacyPolicy.class));
                        break;
                    case R.id.Director_side_nav:
                        startActivity(new Intent(getApplicationContext(), PrivacyPolicy.class));
                        break;
                    case R.id.About_side_nav:
                        startActivity(new Intent(getApplicationContext(), PrivacyPolicy.class));
                        break;
                    case R.id.Notification_side_nav:
                        break;
                    case R.id.Review_us_side_nav:
                        break;
                    case R.id.Logout_side_nav:

                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), loginSignIn.class));
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }
                return false;
            }
        });



    }
    protected void allocateActivityTitle (String titleString){
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titleString);
        }
    }
}