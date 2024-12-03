package com.example.proyectoapp;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

public class WalkDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    private TextView textViewServiceName, textViewPetName, textViewDuration, textViewServiceDate, textViewWalkerName;
    private MapView mapView;
    private RatingBar ratingBar;
    private GoogleMap googleMap;

    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_walk_detail);

        textViewServiceName = findViewById(R.id.textViewServiceName);
        textViewPetName = findViewById(R.id.textViewPetName);
        textViewDuration = findViewById(R.id.textViewDuration);
        textViewServiceDate = findViewById(R.id.textViewServiceDate);
        textViewWalkerName = findViewById(R.id.textViewWalkerName);
        ratingBar = findViewById(R.id.ratingBar);
        mapView = findViewById(R.id.mapView);

        // Obtener datos del Intent
        String serviceName = getIntent().getStringExtra("serviceName");
        String petName = getIntent().getStringExtra("petName");
        String duration = getIntent().getStringExtra("duration");
        String serviceDate = getIntent().getStringExtra("serviceDate");
        String walkerName = getIntent().getStringExtra("walkerName");

        // Asignar valores a los TextView
        textViewServiceName.setText("Paseo: " + serviceName);
        textViewPetName.setText("Mascota: " + petName);
        textViewDuration.setText("Duración: " + duration);
        textViewServiceDate.setText("Fecha: " + serviceDate);
        textViewWalkerName.setText("Paseador: " + walkerName);


        // Configurar RatingBar
        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            Toast.makeText(this, "Calificación: " + rating, Toast.LENGTH_SHORT).show();
            // Aquí puedes guardar la calificación o procesarla
        });

        // Configurar el MapView
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new com.google.android.gms.maps.model.LatLng(-34.6037, -58.3816), 14));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}