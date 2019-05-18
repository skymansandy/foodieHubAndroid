package in.codeshuffle.foodiehub.ui.location.locationlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import in.codeshuffle.foodiehub.R;
import in.codeshuffle.foodiehub.data.network.model.LocationResponse.LocationSuggestion;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private final Context context;
    private final List<LocationSuggestion> locations;
    private final LayoutInflater inflater;
    private final LocationListInterface locationListInterface;

    public LocationAdapter(Context context, LocationListInterface locationListInterface, List<LocationSuggestion> locations) {
        this.context = context;
        this.locationListInterface = locationListInterface;
        this.locations = locations;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_location, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        LocationSuggestion location = locations.get(position);
        holder.tvName.setText(location.getCityName());
        holder.root.setOnClickListener(v->{
            if(locationListInterface !=null){
                locationListInterface.onLocationSelected(
                        location.getCityName(), location.getLatitude(), location.getLongitude());
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public void addLocation(List<LocationSuggestion> newLocations) {
        locations.clear();
        locations.addAll(newLocations);
        notifyDataSetChanged();
    }

    public void clearLocations() {
        locations.clear();
    }

    static class LocationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.root)
        View root;
        @BindView(R.id.name)
        TextView tvName;

        LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface LocationListInterface{
        void onLocationSelected(String city, Double latitude, Double longitude);
    }
}
