package com.safewalk.coimbatore.pro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class JunctionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junctions);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setupButton(R.id.btnAnnaSilai, "Anna Silai");
        setupButton(R.id.btnGandhipuram, "Gandhipuram");
        setupButton(R.id.btnSinganallur, "Singanallur");
        setupButton(R.id.btnLakshmiMills, "Lakshmi Mills");
        setupButton(R.id.btnUkkadam, "Ukkadam");
    }

    private void setupButton(int resId, final String junctionName) {
        Button btn = findViewById(resId);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JunctionsActivity.this, JunctionDetailActivity.class);
                intent.putExtra("JUNCTION_NAME", junctionName);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
