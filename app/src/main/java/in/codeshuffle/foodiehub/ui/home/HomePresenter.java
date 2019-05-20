package in.codeshuffle.foodiehub.ui.home;

import android.util.Log;

import javax.inject.Inject;

import in.codeshuffle.foodiehub.data.DataManager;
import in.codeshuffle.foodiehub.data.network.ApiHeader;
import in.codeshuffle.foodiehub.data.network.model.RestaurantsResponse;
import in.codeshuffle.foodiehub.ui.base.BasePresenter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter<V extends HomeMvpView> extends BasePresenter<V>
        implements HomeMvpPresenter<V> {

    private static final String TAG = HomePresenter.class.getSimpleName();

    @Inject
    HomePresenter(DataManager dataManager, ApiHeader apiHeader) {
        super(dataManager, apiHeader);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        if (getDataManager().isPreferenceMyLocation()) {
            checkLocationPermissionsStatus();
        }
    }

    private void checkLocationPermissionsStatus() {
        getMvpView().setupLocationService();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void fetchRestaurants(String query, Double lat, Double lon) {
        getMvpView().showLoading();

        getDataManager().getRestaurants(getApiHeaders(), query, lat, lon)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RestaurantsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RestaurantsResponse restaurantsResponse) {
                        if (!isViewAttached())
                            return;

                        getMvpView().onRestaurantList(restaurantsResponse);
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (!isViewAttached())
                            return;

                        Log.d(TAG, "onError: " + t.getLocalizedMessage());
                        getMvpView().onError("Something went wrong");
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void saveMyLocation(double lat, double lng, String city, String street) {
        getDataManager().saveLocationInfo(true, lat, lng, city, street);
    }

    @Override
    public void clearLocationPreference() {
        getDataManager().saveLocationInfo(false, 0d, 0d, "", "");
    }

    @Override
    public void handleApiError() {
        super.handleApiError();
    }
}
