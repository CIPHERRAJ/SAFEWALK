package com.safewalk.coimbatore.pro;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import com.safewalk.coimbatore.pro.databinding.ActivityJunctionDetailBinding;
import java.util.Locale;

public class JunctionDetailActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private ActivityJunctionDetailBinding binding;
    private MapView map = null;
    private TextToSpeech tts;
    private String tamilDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        binding = ActivityJunctionDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Setup toolbar
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        String name = getIntent().getStringExtra("JUNCTION_NAME");
        String details = getIntent().getStringExtra("JUNCTION_DETAILS");
        tamilDetails = getIntent().getStringExtra("JUNCTION_TAMIL_DETAILS");
        String risk = getIntent().getStringExtra("JUNCTION_RISK");
        String riskTamil = getTamilRisk(risk);
        double lat = getIntent().getDoubleExtra("JUNCTION_LAT", 11.0183);
        double lng = getIntent().getDoubleExtra("JUNCTION_LNG", 76.9682);

        // Initialize TTS after getting the data
        tts = new TextToSpeech(this, this);

        binding.textViewDetailName.setText(name);
        String combinedAnalysis = details + "\n\n" + tamilDetails;
        binding.textViewDetailAnalysis.setText(combinedAnalysis);
        binding.textViewDetailRisk.setText("Safety Score: " + risk + " (" + riskTamil + ")");

        binding.btnListen.setOnClickListener(v -> speakTamil());

        // Setup OSM Map
        map = binding.mapView;
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);

        GeoPoint startPoint = new GeoPoint(lat, lng);
        map.getController().setZoom(17.5);
        map.getController().setCenter(startPoint);

        Marker startMarker = new Marker(map);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setTitle(name);
        map.getOverlays().add(startMarker);
    }

    private String getTamilRisk(String risk) {
        if (risk == null) return "தெரியவில்லை";
        switch (risk) {
            case "High Risk": return "அதிக அபாயம்";
            case "Moderate Risk": return "மிதமான அபாயம்";
            case "Low Risk": return "குறைந்த அபாயம்";
            default: return risk;
        }
    }

    private void speakTamil() {
        if (tamilDetails != null && !tamilDetails.isEmpty()) {
            tts.speak(tamilDetails, TextToSpeech.QUEUE_FLUSH, null, "TamilTTS");
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(new Locale("ta", "IN"));
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "Tamil language not supported for TTS", Toast.LENGTH_SHORT).show();
            } else {
                // Automatically play the voice when ready
                speakTamil();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (map != null) map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (map != null) map.onPause();
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
