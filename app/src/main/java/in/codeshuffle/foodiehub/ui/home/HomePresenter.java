package in.codeshuffle.foodiehub.ui.home;

import android.util.Log;

import javax.inject.Inject;

import in.codeshuffle.foodiehub.data.network.ApiClient;
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
    HomePresenter(ApiClient apiClient, ApiHeader apiHeader) {
        super(apiClient, apiHeader);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        checkLocationPermissionsStatus();
    }

    private void checkLocationPermissionsStatus() {
        getMvpView().setupLocationService();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void fetchRestaurantsNearMe(Double lat, Double lon) {
        getMvpView().showLoading();

        getApiClient().getRestaurants(getApiHeaders(), lat, lon)
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
    public void onActivityRestart() {
        getMvpView().hideKeyboard();
    }

    @Override
    public void handleApiError() {
        super.handleApiError();
    }
}
