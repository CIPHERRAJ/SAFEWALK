package com.safewalk.coimbatore.pro;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.safewalk.coimbatore.pro.databinding.ActivityJunctionsBinding;
import java.util.ArrayList;
import java.util.List;

public class JunctionsActivity extends AppCompatActivity {

    private ActivityJunctionsBinding binding;
    private JunctionViewModel mJunctionViewModel;
    private List<Junction> allJunctions = new ArrayList<>();
    private JunctionListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJunctionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        adapter = new JunctionListAdapter(new JunctionListAdapter.JunctionDiff(), junction -> {
            Intent intent = new Intent(JunctionsActivity.this, JunctionDetailActivity.class);
            intent.putExtra("JUNCTION_NAME", junction.getName());
            intent.putExtra("JUNCTION_DETAILS", junction.getDetails());
            intent.putExtra("JUNCTION_TAMIL_DETAILS", junction.getTamilDetails());
            intent.putExtra("JUNCTION_RISK", junction.getSafetyScore());
            intent.putExtra("JUNCTION_LAT", junction.getLatitude());
            intent.putExtra("JUNCTION_LNG", junction.getLongitude());
            startActivity(intent);
        });

        binding.recyclerViewJunctions.setAdapter(adapter);
        binding.recyclerViewJunctions.setLayoutManager(new LinearLayoutManager(this));

        mJunctionViewModel = new ViewModelProvider(this).get(JunctionViewModel.class);
        mJunctionViewModel.getAllJunctions().observe(this, junctions -> {
            allJunctions = junctions;
            adapter.submitList(junctions);
        });

        binding.fabViewMap.setOnClickListener(v -> {
            startActivity(new Intent(JunctionsActivity.this, MapActivity.class));
        });

        // Search logic
        binding.searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterJunctions(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterJunctions(newText);
                return true;
            }
        });
    }

    private void filterJunctions(String query) {
        if (query.isEmpty()) {
            adapter.submitList(allJunctions);
        } else {
            List<Junction> filteredList = new ArrayList<>();
            for (Junction junction : allJunctions) {
                if (junction.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(junction);
                }
            }
            adapter.submitList(filteredList);
        }
    }
}
