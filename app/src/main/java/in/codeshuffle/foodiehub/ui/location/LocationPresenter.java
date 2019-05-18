package in.codeshuffle.foodiehub.ui.location;

import android.util.Log;

import javax.inject.Inject;

import in.codeshuffle.foodiehub.data.network.ApiClient;
import in.codeshuffle.foodiehub.data.network.ApiHeader;
import in.codeshuffle.foodiehub.data.network.model.LocationResponse;
import in.codeshuffle.foodiehub.ui.base.BasePresenter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LocationPresenter<V extends LocationMvpView> extends BasePresenter<V>
        implements LocationMvpPresenter<V> {

    @Inject
    LocationPresenter(ApiClient apiClient, ApiHeader apiHeader) {
        super(apiClient, apiHeader);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
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
    public void fetchLocations(String query) {
        Log.d("Location", "fetchLocations: " + query);

        getMvpView().showLoading();

        getApiClient().getLocations(getApiHeaders(),
                query, 12.814301500000001, 77.6798622)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LocationResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LocationResponse locationResponse) {
                        if (!isViewAttached())
                            return;

                        getMvpView().onLocationList(locationResponse);
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (!isViewAttached())
                            return;

                        getMvpView().onError("Something went wrong");
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
