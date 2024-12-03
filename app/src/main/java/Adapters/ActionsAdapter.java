package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoapp.R;

import java.util.List;

public class ActionsAdapter extends RecyclerView.Adapter<ActionsAdapter.ViewHolder> {
    private final List<String> actions;
    private final OnActionClickListener listener;

    public interface OnActionClickListener {
        void onActionClick(String action);
    }
    public ActionsAdapter(List<String> actions, OnActionClickListener listener) {
        this.actions = actions;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_action, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String action = actions.get(position);
        holder.tvActionName.setText(action);
        holder.itemView.setOnClickListener(v -> listener.onActionClick(action));
    }

    @Override
    public int getItemCount() {
        return actions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvActionName;

        ViewHolder(View itemView) {
            super(itemView);
            tvActionName = itemView.findViewById(R.id.tvActionName);
        }
    }
}
