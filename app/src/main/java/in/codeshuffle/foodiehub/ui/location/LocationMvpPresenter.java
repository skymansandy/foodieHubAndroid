package in.codeshuffle.foodiehub.ui.location;

import in.codeshuffle.foodiehub.di.PerActivity;
import in.codeshuffle.foodiehub.ui.base.MvpPresenter;


@PerActivity
public interface LocationMvpPresenter<V extends LocationMvpView> extends MvpPresenter<V> {

    void fetchLocations(String query, Double lat, Double lng);

    void onBackPressed();

    void saveLocationInfo(boolean isMyLocation, Double latitude, Double longitude, String city, String locality);
}
