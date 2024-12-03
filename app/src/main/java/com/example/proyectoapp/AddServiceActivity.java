package com.example.proyectoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_service);

        EditText editTextDuration = findViewById(R.id.editTextDuration);
        EditText editTextPetName = findViewById(R.id.editTextPetName);
        EditText editTextServiceDate = findViewById(R.id.editTextServiceDate);
        EditText editTextWalkerName = findViewById(R.id.editTextWalkerName);

        Button buttonSaveService = findViewById(R.id.buttonSaveService);
        buttonSaveService.setOnClickListener(v -> {
            String duration = editTextDuration.getText().toString();
            String petName = editTextPetName.getText().toString();
            String serviceDate = editTextServiceDate.getText().toString();
            String walkerName = editTextWalkerName.getText().toString();

            if (duration.isEmpty() || petName.isEmpty() || serviceDate.isEmpty() || walkerName.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("SERVICE_DURATION", duration);
                resultIntent.putExtra("PET_NAME", petName);
                resultIntent.putExtra("SERVICE_DATE", serviceDate);
                resultIntent.putExtra("WALKER_NAME", walkerName);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}