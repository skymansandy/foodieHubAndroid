package in.codeshuffle.foodiehub.ui.home.restaurantlist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import in.codeshuffle.foodiehub.R;
import in.codeshuffle.foodiehub.util.CommonUtils;

import static in.codeshuffle.foodiehub.data.network.model.RestaurantsResponse.Restaurant;
import static in.codeshuffle.foodiehub.data.network.model.RestaurantsResponse.Restaurants;

public class RestaurantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_RESTAURANT = 1;
    private static final int VIEW_TYPE_LOADING = 2;

    private final Context context;
    private final List<Restaurants> restaurants;
    private final LayoutInflater inflater;
    private final RestaurantListInterface restaurantListInterface;
    private final Animation pushDownAnim;
    private final Animation pullUpUpAnim;
    private boolean isLoadingMoreRestaurants = false;

    @Inject
    List<String> thumbImages;

    public RestaurantAdapter(Context context, RestaurantListInterface restaurantListInterface,
                             List<Restaurants> restaurants) {
        this.context = context;
        this.pushDownAnim = AnimationUtils.loadAnimation(context, R.anim.press_down);
        this.pullUpUpAnim = AnimationUtils.loadAnimation(context, R.anim.press_up);
        this.restaurantListInterface = restaurantListInterface;
        this.restaurants = restaurants;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_RESTAURANT) {
            View view = inflater.inflate(R.layout.item_restaurant, parent, false);
            return new RestaurantViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_restaurant_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RestaurantViewHolder) {
            Restaurant restaurant = restaurants.get(position).getRestaurant();
            RestaurantViewHolder restaurantHolder = (RestaurantViewHolder) holder;

            restaurantHolder.tvName.setText(restaurant.getName());
            restaurantHolder.tvCuisine.setText(restaurant.getCuisines());
            restaurantHolder.tvLocation.setText(restaurant.getLocation().getLocalityVerbose());
            restaurantHolder.tvCostForTwo.setText(String.format("%s%s for two people (approx.)",
                    restaurant.getCurrency(), restaurant.getAverageCostForTwo()));

            //Rating
            restaurantHolder.tvRating.setText(restaurant.getUserRating().getAggregateRating());
            restaurantHolder.tvRating.setBackgroundColor(
                    Color.parseColor(String.format("#%s", restaurant.getUserRating().getRatingColor())));

            //Online delivery
            if (restaurant.hasOnlineDelivery().equals("1")) {
                restaurantHolder.tvOrderOnline.setVisibility(View.VISIBLE);
                restaurantHolder.tvOrderOnline.setOnClickListener(v
                        -> CommonUtils.showShortToast(context, context.getString(R.string.online_order)));
                if (restaurant.isDeliveringNow().equals("1")) {

                } else {

                }
            } else {
                restaurantHolder.tvOrderOnline.setVisibility(View.GONE);
            }

            //Table booking
            if (restaurant.isTableReservationSupported().equals("1")) {
                restaurantHolder.tvBookTable.setVisibility(View.VISIBLE);
                restaurantHolder.tvBookTable.setOnClickListener(v
                        -> CommonUtils.showShortToast(context, context.getString(R.string.table_booking)));
                if (restaurant.hasTableBooking().equals("1")) {

                } else {

                }
            } else {
                restaurantHolder.tvBookTable.setVisibility(View.GONE);
            }

            //Image previes
            restaurantHolder.rvThumbnailList.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            restaurantHolder.rvThumbnailList.setAdapter(new ImagePreviewAdapter(context,
                    restaurant.getId(), restaurant.getPhotosUrl(),
                    restaurant.getName(),
                    restaurant.getThumb(),
                    restaurantListInterface,
                    CommonUtils.getRandomImages()));
            restaurantHolder.root.setOnClickListener(v -> {
                if (restaurantListInterface != null) {
                    restaurantListInterface.onOpenRestaurantDetail(restaurant.getId());
                }
            });

//        Press animation
        /*restaurantHolder.root.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                restaurantHolder.root.startAnimation(pushDownAnim);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                restaurantHolder.root.startAnimation(pullUpUpAnim);
                restaurantHolder.root.performClick();
                if (restaurantListInterface != null) {
                    restaurantListInterface.onOpenRestaurantDetail(restaurant.getId());
                }
            }
            return true;
        });*/
        }
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public void loadMoreRestaurants() {
        if (!isLoadingMoreRestaurants) {
            isLoadingMoreRestaurants = true;
            restaurants.add(null);
            notifyItemChanged(restaurants.size());

            if (restaurantListInterface != null)
                restaurantListInterface.onLoadMoreRestaurants(restaurants.size());
        }
    }

    public void clearRestaurants() {
        restaurants.clear();
        notifyDataSetChanged();
    }

    public void addRestaurants(List<Restaurants> newRestaurantList) {
        restaurants.addAll(newRestaurantList);
        notifyDataSetChanged();
    }

    public void addMoreRestaurants(List<Restaurants> newRestaurants) {
        restaurants.remove(restaurants.size() - 1);
        notifyDataSetChanged();

        if(newRestaurants == null) return;

        restaurants.addAll(newRestaurants);
        isLoadingMoreRestaurants = false;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return restaurants.get(position) == null ?
                VIEW_TYPE_LOADING : VIEW_TYPE_RESTAURANT;
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.restaurantRoot)
        View root;
        @BindView(R.id.imagesRecycler)
        RecyclerView rvThumbnailList;
        @BindView(R.id.name)
        TextView tvName;
        @BindView(R.id.rating)
        TextView tvRating;
        @BindView(R.id.cuisine)
        TextView tvCuisine;
        @BindView(R.id.location)
        TextView tvLocation;
        @BindView(R.id.costForTwo)
        TextView tvCostForTwo;

        @BindView(R.id.bookTable)
        View tvBookTable;
        @BindView(R.id.orderOnline)
        View tvOrderOnline;

        RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface RestaurantListInterface{
        void onOpenRestaurantDetail(String restaurantId);

        void onImagePreviewClicked(String restaurantId, String imageUrl, String restaurantName,
                                   String restaurantThumb);

        void onSeeAllPreview(String imagesUrl);

        void onLoadMoreRestaurants(int skip);
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {

        LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
