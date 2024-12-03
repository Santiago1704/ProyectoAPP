package com.example.proyectoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import ViewModel.UserViewModel;
import data.entity.UserEntity;
import data.repository.UserRepository;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private TextView registerTextView;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        registerTextView = findViewById(R.id.textViewRegister);

        loginButton.setOnClickListener(v -> loginUser());
        registerTextView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RegisterActivity.class)));
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getLoginUserLiveData().observe(this, user -> {
            if (user != null) {
                if ("Santiago".equalsIgnoreCase(user.getFirst_name())) {
                    // Redirigir al administrador
                    Intent adminIntent = new Intent(MainActivity.this, AdminActivity.class);
                    adminIntent.putExtra("USER_NAME", user.getFirst_name());
                    startActivity(adminIntent);
                } else {
                    // Redirigir al usuario regular
                    Intent intent = new Intent(MainActivity.this, StartActivity.class);
                    intent.putExtra("USER_NAME", user.getFirst_name());
                    startActivity(intent);
                }
                finish(); // Finaliza la actividad actual
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loginUser() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        userViewModel.loginUser(username, password);
    }
}