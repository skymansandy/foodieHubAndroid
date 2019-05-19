package in.codeshuffle.foodiehub.ui.home;

import in.codeshuffle.foodiehub.di.PerActivity;
import in.codeshuffle.foodiehub.ui.base.MvpPresenter;


@PerActivity
public interface HomeMvpPresenter<V extends HomeMvpView> extends MvpPresenter<V> {

    void fetchRestaurantsNearMe(String query, Double lat, Double lon);

    void onActivityRestart();

}
