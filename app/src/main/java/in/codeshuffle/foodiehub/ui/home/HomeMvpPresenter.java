package in.codeshuffle.foodiehub.ui.home;

import java.util.List;

import in.codeshuffle.foodiehub.data.db.model.Restaurant;
import in.codeshuffle.foodiehub.di.PerActivity;
import in.codeshuffle.foodiehub.ui.base.MvpPresenter;


@PerActivity
public interface HomeMvpPresenter<V extends HomeMvpView> extends MvpPresenter<V> {

    void fetchRestaurantsNearMe();

    void showRestaurantsOnView(List<Restaurant> restaurants);

    void showMoreRestaurantsOnView(List<Restaurant> restaurants);
}
