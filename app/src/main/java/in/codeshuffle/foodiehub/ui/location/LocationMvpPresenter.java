package in.codeshuffle.foodiehub.ui.location;

import in.codeshuffle.foodiehub.di.PerActivity;
import in.codeshuffle.foodiehub.ui.base.MvpPresenter;


@PerActivity
public interface LocationMvpPresenter<V extends LocationMvpView> extends MvpPresenter<V> {

    void fetchRestaurantsNearMe();
}
