package com.safewalk.coimbatore.pro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Main Home Screen of SafeWalk Coimbatore PRO.
 * Provides navigation to Junctions, Safety Tips, and Report Issue screens.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        Button btnJunctions = findViewById(R.id.btnJunctions);
        Button btnTips = findViewById(R.id.btnTips);
        Button btnReport = findViewById(R.id.btnReport);

        // Set click listener for Junctions Directory
        btnJunctions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JunctionsActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for Safety Tips
        btnTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TipsActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for Report Issue
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });
    }
}
