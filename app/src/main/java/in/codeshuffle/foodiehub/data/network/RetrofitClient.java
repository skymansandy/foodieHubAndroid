package in.codeshuffle.foodiehub.data.network;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import in.codeshuffle.foodiehub.data.network.model.ApiError;
import in.codeshuffle.foodiehub.data.network.model.LocationRequest;
import in.codeshuffle.foodiehub.data.network.model.LocationResponse;
import in.codeshuffle.foodiehub.data.network.model.RestaurantDetailResponse;
import in.codeshuffle.foodiehub.data.network.model.RestaurantsResponse;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RetrofitClient {

    private ApiHelper networkService;

    private RetrofitClient() {
    }

    public RetrofitClient(ApiHelper apiHelper) {
        this.networkService = apiHelper;
    }


    public Observable<LocationResponse> getLocationList(LocationRequest locationRequest, GetLocationListCallback callback) {

        return networkService.getLocations(locationRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext((Function<Throwable, ObservableSource<? extends LocationResponse>>) Observable::error);
    }









    public interface GetRestaurantListCallback{
        void onSuccess(RestaurantsResponse restaurantsResponse);

        void onError(ApiError apiError);
    }

    public interface GetRestaurantDetailCallback{
        void onSuccess(RestaurantDetailResponse restaurantDetailResponse);

        void onError(ApiError apiError);
    }

    public interface GetLocationListCallback{
        void onSuccess(LocationResponse locationResponse);

        void onError(ApiError apiError);
    }
}
