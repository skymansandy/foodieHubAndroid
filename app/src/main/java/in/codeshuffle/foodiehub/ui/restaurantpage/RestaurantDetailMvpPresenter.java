package in.codeshuffle.foodiehub.ui.restaurantpage;

import in.codeshuffle.foodiehub.di.PerActivity;
import in.codeshuffle.foodiehub.ui.base.MvpPresenter;


@PerActivity
public interface RestaurantDetailMvpPresenter<V extends RestaurantDetailMvpView> extends MvpPresenter<V> {

    void fetchRestaurantDetails(Long restaurantId);
}
