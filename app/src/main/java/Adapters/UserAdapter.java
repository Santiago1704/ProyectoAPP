package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoapp.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.entity.UserEntity;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<UserEntity> users;
    private Context context;

    public UserAdapter(List<UserEntity> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        UserEntity user = users.get(position);
        holder.tvFirstName.setText(user.getFirst_name());
        holder.tvLastName.setText(user.getLast_name());
        holder.tvUsername.setText(user.getUser());
        Map<Integer, Runnable> menuActions = new HashMap<>();
        menuActions.put(R.id.menu_edit, () -> {
            Toast.makeText(context, "Editar " + user.getFirst_name(), Toast.LENGTH_SHORT).show();
        });
        menuActions.put(R.id.menu_delete, () -> {
            Toast.makeText(context, "Eliminar " + user.getFirst_name(), Toast.LENGTH_SHORT).show();
        });

        holder.ivMenu.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, holder.ivMenu);
            popupMenu.inflate(R.menu.menu_user_options);

            popupMenu.setOnMenuItemClickListener(item -> {
                Runnable action = menuActions.get(item.getItemId());
                if (action != null) {
                    action.run();
                    return true;
                }
                return false;
            });

            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvFirstName, tvLastName, tvUsername;
        ImageView ivMenu;

        public UserViewHolder(View itemView) {
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivMenu = itemView.findViewById(R.id.ivMenu);
        }
    }
}
