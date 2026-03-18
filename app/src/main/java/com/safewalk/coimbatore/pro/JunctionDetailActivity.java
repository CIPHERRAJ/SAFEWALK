package com.safewalk.coimbatore.pro;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class JunctionDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junction_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String junction = getIntent().getStringExtra("JUNCTION_NAME");
        if (junction == null) junction = "Unknown";

        TextView tvTitle = findViewById(R.id.tvDetailTitle);
        TextView tvTraffic = findViewById(R.id.tvTraffic);
        TextView tvSignal = findViewById(R.id.tvSignal);
        TextView tvRisk = findViewById(R.id.tvRisk);
        TextView tvTip = findViewById(R.id.tvContextualTip);

        tvTitle.setText(junction);

        // Simulated Hardcoded Analysis Data
        switch (junction) {
            case "Anna Silai":
                tvTraffic.setText("TRAFFIC: Moderate");
                tvSignal.setText("SIGNAL: Available");
                tvRisk.setText("RISK LEVEL: Low");
                tvTip.setText("TIP: Use the marked pedestrian path near the statue.");
                break;
            case "Gandhipuram":
                tvTraffic.setText("TRAFFIC: High");
                tvSignal.setText("SIGNAL: Available");
                tvRisk.setText("RISK LEVEL: High");
                tvTip.setText("TIP: Use the skywalk to cross the flyover area.");
                break;
            case "Singanallur":
                tvTraffic.setText("TRAFFIC: High");
                tvSignal.setText("SIGNAL: Limited");
                tvRisk.setText("RISK LEVEL: High");
                tvTip.setText("TIP: Watch for buses turning from the terminus.");
                break;
            case "Lakshmi Mills":
                tvTraffic.setText("TRAFFIC: High");
                tvSignal.setText("SIGNAL: Available");
                tvRisk.setText("RISK LEVEL: Moderate");
                tvTip.setText("TIP: Cross only when the pedestrian signal is green.");
                break;
            case "Ukkadam":
                tvTraffic.setText("TRAFFIC: Very High");
                tvSignal.setText("SIGNAL: Available");
                tvRisk.setText("RISK LEVEL: High");
                tvTip.setText("TIP: Be cautious of two-wheelers near the bus stand.");
                break;
            default:
                tvTraffic.setText("TRAFFIC: Unknown");
                tvSignal.setText("SIGNAL: Unknown");
                tvRisk.setText("RISK LEVEL: Unknown");
                tvTip.setText("TIP: General safety rules apply.");
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
