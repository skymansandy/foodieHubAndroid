package in.codeshuffle.foodiehub.ui.restaurantpage;

import in.codeshuffle.foodiehub.data.network.model.RestaurantDetailResponse;
import in.codeshuffle.foodiehub.ui.base.MvpView;

public interface RestaurantDetailMvpView extends MvpView {
    void onRestaurantDetail(RestaurantDetailResponse restaurantDetailResponse);
}
