package in.codeshuffle.foodiehub.ui.home;

import java.util.List;

import javax.inject.Inject;

import in.codeshuffle.foodiehub.data.db.model.Restaurant;
import in.codeshuffle.foodiehub.data.network.ApiHelper;
import in.codeshuffle.foodiehub.data.network.model.LocationRequest;
import in.codeshuffle.foodiehub.data.network.model.LocationResponse;
import in.codeshuffle.foodiehub.ui.base.BasePresenter;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomePresenter<V extends HomeMvpView> extends BasePresenter<V>
        implements HomeMvpPresenter<V> {

    private ApiHelper apiHelper;

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
        getMvpView().showLoading();

        apiHelper.getLocations(new LocationRequest())
                .subscribe(new Observer<LocationResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LocationResponse locationResponse) {
                        getMvpView().onLocationList(locationResponse);
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable t) {
                        getMvpView().onError("Something went wrong");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void showRestaurantsOnView(List<Restaurant> restaurants) {

    }

    @Override
    public void showMoreRestaurantsOnView(List<Restaurant> restaurants) {

    }
}
