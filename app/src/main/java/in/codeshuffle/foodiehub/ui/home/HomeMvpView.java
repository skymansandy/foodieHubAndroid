package in.codeshuffle.foodiehub.ui.home;

import java.util.List;

import in.codeshuffle.foodiehub.data.db.model.Restaurant;
import in.codeshuffle.foodiehub.data.network.model.RestaurantsResponse;
import in.codeshuffle.foodiehub.ui.base.MvpView;

public interface HomeMvpView extends MvpView {
    void setupLocationService();

    void onRestaurantList(RestaurantsResponse restaurantsResponse);
}
