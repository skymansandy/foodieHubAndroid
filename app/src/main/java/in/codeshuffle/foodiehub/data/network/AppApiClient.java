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
public class AppApiClient implements ApiClient {

    private ApiHeader mApiHeader;
    private ApiClient apiClient;

    @Inject
    AppApiClient(ApiHeader apiHeader, ApiClient apiClient) {
        this.mApiHeader = apiHeader;
        this.apiClient = apiClient;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Observable<RestaurantsResponse> getRestaurants(RestaurantsRequest restaurantsRequest) {
        return apiClient.getRestaurants(restaurantsRequest);
    }

    @Override
    public Observable<RestaurantDetailResponse> getRestaurantDetail(
            Map<String, String> apiKey, Long restaurantId) {
        return apiClient.getRestaurantDetail(apiKey, restaurantId);
    }

    @Override
    public Observable<LocationResponse> getLocations(Map<String, String> apiKey, String query, Double lat, Double lon) {
        return apiClient.getLocations(apiKey, query, lat, lon);
    }
}

