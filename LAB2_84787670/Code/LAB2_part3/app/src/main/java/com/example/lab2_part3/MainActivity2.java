package com.example.lab2_part3;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Usa la API web de Google Maps para mostrar tres marcadores y la ruta entre ellos
        String url = "https://www.google.com/maps/dir/?api=1"
                + "&origin=Kelowna,BC"                  // punto A
                + "&destination=Lake+Country,BC"        // punto C
                + "&waypoints=UBC+Okanagan,BC"          // punto B
                + "&travelmode=driving";

        Intent mapsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        mapsIntent.setPackage("com.google.android.apps.maps");

        try {
            // Intentar abrir la app de Google Maps
            startActivity(mapsIntent);
        } catch (ActivityNotFoundException e) {
            // Si no hay app, abre el navegador
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(webIntent);
        }

        // Cierra esta actividad para volver a MainActivity al salir de Maps
        finish();
    }
}

