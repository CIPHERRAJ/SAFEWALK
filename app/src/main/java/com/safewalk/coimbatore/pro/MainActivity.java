package com.safewalk.coimbatore.pro;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.safewalk.coimbatore.pro.databinding.ActivityMainBinding;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }

        // Entrance Animations
        Animation fadeInUp = AnimationUtils.loadAnimation(this, R.anim.fade_in_up);
        binding.cardJunctions.startAnimation(fadeInUp);
        binding.cardTips.startAnimation(fadeInUp);
        binding.cardReport.startAnimation(fadeInUp);

        binding.cardJunctions.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, JunctionsActivity.class));
        });

        binding.cardTips.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TipsActivity.class));
        });

        binding.cardReport.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ReportActivity.class));
        });
    }
}
