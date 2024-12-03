package com.example.proyectoapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Adapters.UserAdapter;
import data.entity.UserEntity;

public class UserActivity extends AppCompatActivity {
    private RecyclerView rvUsers;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);

        rvUsers = findViewById(R.id.rvUsers);
        tvTitle = findViewById(R.id.tvTitle);

        String title = getIntent().getStringExtra("title");
        List<UserEntity> users = (List<UserEntity>) getIntent().getSerializableExtra("users");

        tvTitle.setText(title);

        if (users != null && !users.isEmpty()) {
            UserAdapter userAdapter = new UserAdapter(users, this);
            rvUsers.setLayoutManager(new LinearLayoutManager(this));
            rvUsers.setAdapter(userAdapter);
        } else {
            Toast.makeText(this, "No hay usuarios disponibles", Toast.LENGTH_SHORT).show();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}