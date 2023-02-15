package com.example.ksvcem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ksvcem.databinding.ActivityContactBinding;

public class contact extends drawerBase {

    ActivityContactBinding activityContactBinding;

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
        activityContactBinding = ActivityContactBinding.inflate(getLayoutInflater());
        setContentView(activityContactBinding.getRoot());
//        Objects.requireNonNull(getSupportActionBar()).hide();
        allocateActivityTitle("Support");
        ImageView phone = findViewById(R.id.call);

        ImageView background = (ImageView) findViewById(R.id.support_back);

        Glide.with(this)
                        .load(R.drawable.support)
                                .into(background);

        phone.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:11111111"));
            startActivity(intent);
        });

    }
}