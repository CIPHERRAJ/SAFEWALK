package com.safewalk.coimbatore.pro;

import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import com.safewalk.coimbatore.pro.databinding.ActivityMapBinding;

public class MapActivity extends AppCompatActivity {

    private ActivityMapBinding binding;
    private MapView map = null;
    private JunctionViewModel mJunctionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        map = binding.mapView;
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);

        // Center on Coimbatore
        GeoPoint startPoint = new GeoPoint(11.0183, 76.9682);
        map.getController().setZoom(13.0);
        map.getController().setCenter(startPoint);

        mJunctionViewModel = new ViewModelProvider(this).get(JunctionViewModel.class);
        mJunctionViewModel.getAllJunctions().observe(this, junctions -> {
            map.getOverlays().clear();
            for (Junction junction : junctions) {
                Marker marker = new Marker(map);
                marker.setPosition(new GeoPoint(junction.getLatitude(), junction.getLongitude()));
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                marker.setTitle(junction.getName());
                marker.setSnippet("Safety: " + junction.getSafetyScore());
                
                // Color coding would usually require custom icons, but we'll use title for now
                map.getOverlays().add(marker);
            }
            map.invalidate();
        });
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
}
