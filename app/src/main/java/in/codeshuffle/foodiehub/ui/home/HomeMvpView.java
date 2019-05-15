package in.codeshuffle.foodiehub.ui.home;

import java.util.List;

import in.codeshuffle.foodiehub.data.db.model.Restaurant;
import in.codeshuffle.foodiehub.data.network.model.LocationResponse;
import in.codeshuffle.foodiehub.ui.base.MvpView;

public interface HomeMvpView extends MvpView {
    void checkLocationPermissions();

    void showRestaurants(List<Restaurant> restaurants);

    void showMoreRestaurants(List<Restaurant> restaurants);

    void onLocationList(LocationResponse locationResponse);
}
