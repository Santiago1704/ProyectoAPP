package com.example.proyectoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapters.ActionsAdapter;
import Adapters.WalksAdapter;
import ViewModel.UserViewModel;
import data.entity.UserEntity;

public class AdminActivity extends AppCompatActivity {
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        RecyclerView rvActions = findViewById(R.id.rvActions);
        List<String> adminActions = Arrays.asList("Usuarios", "Paseadores", "Paseos Programados", "Info");
        rvActions.setLayoutManager(new LinearLayoutManager(this));
        rvActions.setAdapter(new ActionsAdapter(adminActions, action -> {
            switch (action) {
                case "Usuarios":
                    showUsers();
                    break;
                case "Paseadores":
                    showPaseadores();
                    break;
                case "Paseos Programados":
                    showWalks();
                    break;
                case "Info":
                    showInfo();
                    break;
            }
        }));

        RecyclerView rvCompletedWalks = findViewById(R.id.rvCompletedWalks);
        List<String> walks = Arrays.asList("Paseo #1 - 2 horas", "Paseo #2 - 1.5 horas");
        rvCompletedWalks.setLayoutManager(new LinearLayoutManager(this));
        rvCompletedWalks.setAdapter(new WalksAdapter(walks));

        ImageView imageViewProfile = findViewById(R.id.ivProfile);
        imageViewProfile.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(AdminActivity.this, v);
            MenuInflater inflater = popupMenu.getMenuInflater();
            inflater.inflate(R.menu.menu_profile_options, popupMenu.getMenu());

            Map<Integer, Runnable> actions = new HashMap<>();
            actions.put(R.id.menu_edit_profile, () -> {
                Toast.makeText(this, "Editar Perfil seleccionado", Toast.LENGTH_SHORT).show();
            });
            actions.put(R.id.menu_logout, () -> {
                Toast.makeText(this, "Cerrar Sesión seleccionado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });

            popupMenu.setOnMenuItemClickListener(item -> {
                Runnable action = actions.get(item.getItemId());
                if (action != null) {
                    action.run();
                    return true;
                }
                return false;
            });

            popupMenu.show();
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showInfo() {
        List<String> info = Arrays.asList("Información General", "Sobre Nosotros", "Contacto");
        Intent intent = new Intent(AdminActivity.this, ListActivity.class);
        intent.putExtra("title", "Info");
        intent.putStringArrayListExtra("items", new ArrayList<>(info));
        startActivity(intent);
    }

    private void showWalks() {
        List<String> paseosProgramados = Arrays.asList("Paseo 1", "Paseo 2", "Paseo 3");
        Intent intent = new Intent(AdminActivity.this, ListActivity.class);
        intent.putExtra("title", "Paseos Programados");
        intent.putStringArrayListExtra("items", new ArrayList<>(paseosProgramados));
        startActivity(intent);
    }

    private void showUsers() {
        if (userViewModel != null) {
            userViewModel.getAllUsers().observe(this, users -> {
                if (users != null) {
                    Intent intent = new Intent(AdminActivity.this, UserActivity.class);
                    intent.putExtra("title", "Usuarios");
                    intent.putExtra("users", (Serializable) users);

                    startActivity(intent);
                } else {
                    Toast.makeText(this, "No se pudieron cargar los usuarios", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Error: ViewModel no inicializado", Toast.LENGTH_SHORT).show();
        }
    }

    private void showPaseadores() {
        List<String> paseadores = Arrays.asList("Paseador 1", "Paseador 2", "Paseador 3");
        Intent intent = new Intent(AdminActivity.this, ListActivity.class);
        intent.putExtra("title", "Paseadores");
        intent.putStringArrayListExtra("items", new ArrayList<>(paseadores));
        startActivity(intent);
    }

}