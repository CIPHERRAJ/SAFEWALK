package com.safewalk.coimbatore.pro;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.safewalk.coimbatore.pro.databinding.ActivityMainBinding;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final int SOS_PERMISSION_REQUEST_CODE = 1001;

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
        binding.fabSos.startAnimation(fadeInUp);

        binding.cardJunctions.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, JunctionsActivity.class));
        });

        binding.cardTips.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TipsActivity.class));
        });

        binding.cardReport.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ReportActivity.class));
        });

        binding.fabSos.setOnClickListener(v -> {
            checkSosPermissions();
        });
    }

    private void checkSosPermissions() {
        String[] permissions = {
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS,
                Manifest.permission.ACCESS_FINE_LOCATION
        };

        boolean allGranted = true;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                allGranted = false;
                break;
            }
        }

        if (allGranted) {
            triggerSos();
        } else {
            ActivityCompat.requestPermissions(this, permissions, SOS_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SOS_PERMISSION_REQUEST_CODE) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }
            if (allGranted) {
                triggerSos();
            } else {
                Toast.makeText(this, R.string.sos_permission_denied, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void triggerSos() {
        // 1. Call Police (100)
        Toast.makeText(this, R.string.sos_calling_police, Toast.LENGTH_SHORT).show();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:100"));
        startActivity(callIntent);

        // 2. Send SMS to emergency contact (placeholder number 100 for now or a test number)
        // In a real app, this would be a user-configured contact
        try {
            SmsManager smsManager = SmsManager.getDefault();
            String message = "EMERGENCY! I need help. My current location is being tracked by SafeWalk Coimbatore.";
            // For demo purposes, we'll just show a toast about SMS
            // smsManager.sendTextMessage("EMERGENCY_CONTACT_NUMBER", null, message, null, null);
            Toast.makeText(this, R.string.sos_sms_sent, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "SMS failed to send", Toast.LENGTH_SHORT).show();
        }
    }
}
