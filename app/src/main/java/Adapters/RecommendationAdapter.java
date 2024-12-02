package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectoapp.R;

import java.util.List;

public class RecommendationAdapter extends ArrayAdapter<Recomendation> {
    private Context context;
    private List<Recomendation> recommendations;

    public RecommendationAdapter(@NonNull Context context, @NonNull List<Recomendation> recommendations) {
        super(context, R.layout.item_recommendation, recommendations);
        this.context = context;
        this.recommendations = recommendations;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recommendation, parent, false);
        }

        Recomendation recommendation = recommendations.get(position);

        ImageView imageView = convertView.findViewById(R.id.imageViewRecommendation);
        TextView titleTextView = convertView.findViewById(R.id.textViewTitle);
        TextView descriptionTextView = convertView.findViewById(R.id.textViewDescription);

        imageView.setImageResource(recommendation.getImageResId());
        titleTextView.setText(recommendation.getTitle());
        descriptionTextView.setText(recommendation.getDescription());

        return convertView;
    }
}
