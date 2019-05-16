package in.codeshuffle.foodiehub.ui.home;

import java.util.List;

import javax.inject.Inject;

import in.codeshuffle.foodiehub.data.db.model.Restaurant;
import in.codeshuffle.foodiehub.data.network.ApiHelper;
import in.codeshuffle.foodiehub.data.network.model.LocationResponse;
import in.codeshuffle.foodiehub.ui.base.BasePresenter;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomePresenter<V extends HomeMvpView> extends BasePresenter<V>
        implements HomeMvpPresenter<V> {

    @Inject
    ApiHelper apiHelper;

    @Inject
    HomePresenter() {
        super();
/*
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    // Customize the request
                    Request request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .removeHeader("Pragma")
                            .header("Cache-Control", String.format("max-age=%s", BuildConfig.CACHETIME))
                            .build();

                    Response response = chain.proceed(request);
                    response.cacheResponse();
                    // Customize or return the response
                    Log.d("API", "HomePresenter: " + response.toString());
                    return response;
                })
                .build();


        apiHelper = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build().create(ApiHelper.class);*/
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

        apiHelper.getLocations(getApiHeaders(),
                12.814301500000001, 77.6798622)
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

    @Override
    public void showRestaurantsOnView(List<Restaurant> restaurants) {

    }

    @Override
    public void showMoreRestaurantsOnView(List<Restaurant> restaurants) {

    }
}
