package in.codeshuffle.foodiehub.ui.home.restaurantlist;

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
import in.codeshuffle.foodiehub.data.network.model.RestaurantsResponse;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private final Context context;
    private final List<RestaurantsResponse.Restaurants> restaurants;
    private final LayoutInflater inflater;

    public RestaurantAdapter(Context context, List<RestaurantsResponse.Restaurants> restaurants) {
        this.context = context;
        this.restaurants = restaurants;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        RestaurantsResponse.Restaurant restaurant = restaurants.get(position).getRestaurant();
        holder.tvTitle.setText(restaurant.getName());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView tvTitle;
        @BindView(R.id.rating)
        TextView tvRating;
        @BindView(R.id.description)
        TextView tvDescription;
        @BindView(R.id.priceForTwo)
        TextView tvPriceForTwo;
        @BindView(R.id.features)
        TextView tvFeatures;
        @BindView(R.id.deliveryTime)
        TextView tvDeliveryTime;

        RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
