package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.proyectoapp.R;

import java.util.List;

public class WalkServiceAdapter extends ArrayAdapter<WalkService> {
    public WalkServiceAdapter(Context context, List<WalkService> services) {
        super(context, 0, services);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_walk_service, parent, false);
        }
        WalkService currentService = getItem(position);

        TextView textViewServiceName = convertView.findViewById(R.id.textViewServiceName);
        TextView textViewPetName = convertView.findViewById(R.id.textViewPetName);
        TextView textViewDuration = convertView.findViewById(R.id.textViewDuration);
        TextView textViewServiceDate = convertView.findViewById(R.id.textViewServiceDate);

        textViewServiceName.setText(currentService.getServiceName());
        textViewPetName.setText(currentService.getPetName());
        textViewDuration.setText(currentService.getDuration());
        textViewServiceDate.setText(currentService.getServiceDate());

        return convertView;
    }
}
