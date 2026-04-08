package com.example.lab2_part3;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);  // Debe existir y tener @id/map

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        if (mapFragment == null) {
            Toast.makeText(this, "ERROR: mapFragment == null (revisa activity_maps.xml @id/map)", Toast.LENGTH_LONG).show();
            return;
        }
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng kelowna     = new LatLng(49.8801, -119.4436);
        LatLng ubco        = new LatLng(49.9394, -119.3948);
        LatLng lakeCountry = new LatLng(50.0537, -119.4106);

        mMap.addMarker(new MarkerOptions().position(kelowna).title("Kelowna"));
        mMap.addMarker(new MarkerOptions().position(ubco).title("UBCO"));
        mMap.addMarker(new MarkerOptions().position(lakeCountry).title("Lake Country"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kelowna, 10f));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
    }
}
