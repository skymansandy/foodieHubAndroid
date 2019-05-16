package in.codeshuffle.foodiehub.data.network;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.codeshuffle.foodiehub.data.network.model.LocationResponse;
import in.codeshuffle.foodiehub.data.network.model.RestaurantDetailRequest;
import in.codeshuffle.foodiehub.data.network.model.RestaurantDetailResponse;
import in.codeshuffle.foodiehub.data.network.model.RestaurantsRequest;
import in.codeshuffle.foodiehub.data.network.model.RestaurantsResponse;
import io.reactivex.Observable;


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
    public Observable<LocationResponse> getLocations(Map<String, String> apiKey, Double lat, Double lon) {
        return apiHelper.getLocations(apiKey, lat, lon);
    }
}

