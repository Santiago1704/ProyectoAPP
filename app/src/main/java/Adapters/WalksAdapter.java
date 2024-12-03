package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoapp.R;

import java.util.List;

public class WalksAdapter extends RecyclerView.Adapter<WalksAdapter.ViewHolder>{
    private final List<String> walks;

    public WalksAdapter(List<String> walks) {
        this.walks = walks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_walk, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String walk = walks.get(position);
        holder.tvWalkDetails.setText(walk);
    }

    @Override
    public int getItemCount() {
        return walks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvWalkDetails;

        ViewHolder(View itemView) {
            super(itemView);
            tvWalkDetails = itemView.findViewById(R.id.tvWalkDetails);
        }
    }
}
