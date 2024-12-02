package com.example.proyectoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import Adapters.Recomendation;
import Adapters.RecommendationAdapter;
import Adapters.WalkService;
import Adapters.WalkServiceAdapter;

public class StartActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_ADD_SERVICE = 1;
    private static final String SHARED_PREFS_NAME = "walk_service_prefs";
    private static final String KEY_WALK_SERVICES = "walk_services";

    private ListView listViewCurrentWalks;
    private WalkServiceAdapter walkServiceAdapter;
    private List<WalkService> currentWalkServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);

        ListView listViewRecommendations = findViewById(R.id.listViewRecommendations);
        listViewCurrentWalks = findViewById(R.id.listViewCurrentWalks);

        currentWalkServices = new ArrayList<>();
        currentWalkServices.add(new WalkService("Paseo 1", "Rex", "2 horas", "01/12/2024"));
        currentWalkServices.add(new WalkService("Paseo 2", "Luna", "1 hora", "02/12/2024"));

        walkServiceAdapter = new WalkServiceAdapter(this, currentWalkServices);
        listViewCurrentWalks.setAdapter(walkServiceAdapter);

        List<Recomendation> recommendations = new ArrayList<>();
        recommendations.add(new Recomendation("Pet Shampoo", "A gentle shampoo for all pets.", R.drawable.ic_pet));
        recommendations.add(new Recomendation("Cat Tree", "Perfect for your cat to play and relax.", R.drawable.ic_cat_tree));
        recommendations.add(new Recomendation("Dog Chew Toy", "Durable toy for your dog to enjoy.", R.drawable.ic_dog_toy));

        RecommendationAdapter adapter = new RecommendationAdapter(this, recommendations);
        listViewRecommendations.setAdapter(adapter);

        WebView webViewTips = findViewById(R.id.videoViewTips);
        WebSettings webSettings = webViewTips.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String videoUrl = "https://www.youtube.com/embed/6SRVnG2QJ10";
        String embedHtml = "<html><body style=\"margin:0;padding:0;\"><iframe width=\"100%\" height=\"100%\" src=\""
                + videoUrl
                + "\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe></body></html>";

        webViewTips.loadData(embedHtml, "text/html", "utf-8");

        webViewTips.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Toast.makeText(StartActivity.this, "Error cargando el video: " + description, Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonAddService = findViewById(R.id.buttonAddService);
        buttonAddService.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, AddServiceActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_SERVICE);
        });

        String userName = getIntent().getStringExtra("USER_NAME");
        TextView welcomeTextView = findViewById(R.id.textViewWelcome);
        welcomeTextView.setText("Hola, " + userName + "!");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_SERVICE && resultCode == RESULT_OK) {
            String duration = data.getStringExtra("SERVICE_DURATION");
            String petName = data.getStringExtra("PET_NAME");
            String serviceDate = data.getStringExtra("SERVICE_DATE");

            if (duration != null && petName != null && serviceDate != null) {
                WalkService newService = new WalkService("Nuevo Paseo", petName, duration, serviceDate);
                currentWalkServices.add(newService);

                // Actualizar la lista
                walkServiceAdapter.notifyDataSetChanged();

                // Guardar en SharedPreferences
                saveWalkServices(currentWalkServices);

                Toast.makeText(this, "Servicio agregado exitosamente", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveWalkServices(List<WalkService> services) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(services);
        editor.putString(KEY_WALK_SERVICES, json);
        editor.apply();
    }

    private List<WalkService> loadWalkServices() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_WALK_SERVICES, null);
        Type type = new TypeToken<List<WalkService>>() {}.getType();
        return gson.fromJson(json, type);
    }
}