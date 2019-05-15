package in.codeshuffle.foodiehub.data.network;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.codeshuffle.foodiehub.BuildConfig;
import in.codeshuffle.foodiehub.data.network.model.LocationRequest;
import in.codeshuffle.foodiehub.data.network.model.LocationResponse;
import in.codeshuffle.foodiehub.data.network.model.RestaurantDetailRequest;
import in.codeshuffle.foodiehub.data.network.model.RestaurantDetailResponse;
import in.codeshuffle.foodiehub.data.network.model.RestaurantsRequest;
import in.codeshuffle.foodiehub.data.network.model.RestaurantsResponse;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;
    private ApiHelper apiHelper;

    @Inject
    public AppApiHelper(ApiHeader apiHeader, ApiHelper apiHelper) {
        this.mApiHeader = apiHeader;
        this.apiHelper = apiHelper;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Observable<RestaurantsResponse> getRestaurants(RestaurantsRequest restaurantsRequest) {
        return apiHelper.getRestaurants(restaurantsRequest);
    }

    @Override
    public Observable<RestaurantDetailResponse> getRestaurantDetail(RestaurantDetailRequest restaurantDetailRequest) {
        return apiHelper.getRestaurantDetail(restaurantDetailRequest);
    }

    @Override
    public Observable<LocationResponse> getLocations(LocationRequest locationRequest) {
        return apiHelper.getLocations(locationRequest);
    }


}

