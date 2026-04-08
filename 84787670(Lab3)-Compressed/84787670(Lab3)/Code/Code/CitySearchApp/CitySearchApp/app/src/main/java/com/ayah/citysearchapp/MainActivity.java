package com.ayah.citysearchapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private ListView cityListView;
    private ArrayAdapter<String> adapter;
    private List<String> allCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
        cityListView = findViewById(R.id.cityListView);

        allCities = Arrays.asList(
                "Toronto", "Vancouver", "Montreal", "Calgary", "Edmonton",
                "Ottawa", "Winnipeg", "Quebec City", "Hamilton", "Halifax",
                "Victoria", "Saskatoon", "Regina", "St. John's", "Kelowna"
        );

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, allCities);
        cityListView.setAdapter(adapter);

        searchView.setQueryHint("Search for city: ");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        cityListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCity = adapter.getItem(position);
            Toast.makeText(MainActivity.this,
                    "Selected: " + selectedCity, Toast.LENGTH_SHORT).show();
        });
    }
}