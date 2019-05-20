package in.codeshuffle.foodiehub.ui.home;

import in.codeshuffle.foodiehub.di.PerActivity;
import in.codeshuffle.foodiehub.ui.base.MvpPresenter;


@PerActivity
public interface HomeMvpPresenter<V extends HomeMvpView> extends MvpPresenter<V> {

    void fetchRestaurants(String query, Double lat, Double lon);

    void saveMyLocation(double lat, double lng, String city, String street);

    void clearLocationPreference();
}
