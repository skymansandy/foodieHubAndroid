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

import static in.codeshuffle.foodiehub.data.network.model.RestaurantsResponse.Restaurant;
import static in.codeshuffle.foodiehub.data.network.model.RestaurantsResponse.Restaurants;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private final Context context;
    private final List<Restaurants> restaurants;
    private final LayoutInflater inflater;
    private final RestaurantListInterface restaurantListInterface;

    public RestaurantAdapter(Context context, RestaurantListInterface restaurantListInterface, List<Restaurants> restaurants) {
        this.context = context;
        this.restaurantListInterface = restaurantListInterface;
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
        Restaurant restaurant = restaurants.get(position).getRestaurant();
        holder.tvTitle.setText(restaurant.getName());
        holder.root.setOnClickListener(v->{
            if(restaurantListInterface!=null){
                restaurantListInterface.onOpenRestaurantDetail(restaurant.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public void addRestaurants(List<Restaurants> newRestaurantList) {
        restaurants.addAll(newRestaurantList);
        notifyDataSetChanged();
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.restaurantRoot)
        View root;
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

    public interface RestaurantListInterface{
        void onOpenRestaurantDetail(String restaurantId);
    }
}
