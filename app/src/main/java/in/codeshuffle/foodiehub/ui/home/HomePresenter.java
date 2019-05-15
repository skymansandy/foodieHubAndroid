package in.codeshuffle.foodiehub.ui.home;

import javax.inject.Inject;

import in.codeshuffle.foodiehub.ui.base.BasePresenter;

public class HomePresenter<V extends HomeMvpView> extends BasePresenter<V>
        implements HomeMvpPresenter<V> {

    private static final long SPLASH_DURATION_MILLIS = 2000;

    @Inject
    HomePresenter() {
        super();
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        checkLocationPermissionsStatus();
    }

    private void checkLocationPermissionsStatus() {
        getMvpView().checkLocationPermissions();
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void handleApiError() {
        super.handleApiError();
    }

    @Override
    public void fetchRestaurantsNearMe() {

    }
}
