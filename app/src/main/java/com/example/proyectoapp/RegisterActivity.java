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

public class RegisterActivity extends AppCompatActivity {
    private EditText firstNameEditText, lastNameEditText, usernameEditText, passwordEditText;
    private Button registerButton;
    private UserViewModel userViewModel;
    private TextView textViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        firstNameEditText = findViewById(R.id.editTextFirstName);
        lastNameEditText = findViewById(R.id.editTextLastName);
        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        textViewLogin = findViewById(R.id.textViewLogin);
        registerButton = findViewById(R.id.buttonRegister);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        textViewLogin.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, MainActivity.class)));

        registerButton.setOnClickListener(v -> registerUser());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void registerUser() {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        UserEntity newUser = new UserEntity(firstName, lastName, username, password);
        userViewModel.insertUser(newUser);
        Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}