package in.codeshuffle.foodiehub.data.network;


import java.util.Map;

import in.codeshuffle.foodiehub.data.network.model.LocationResponse;
import in.codeshuffle.foodiehub.data.network.model.RestaurantDetailRequest;
import in.codeshuffle.foodiehub.data.network.model.RestaurantDetailResponse;
import in.codeshuffle.foodiehub.data.network.model.RestaurantsRequest;
import in.codeshuffle.foodiehub.data.network.model.RestaurantsResponse;
import in.codeshuffle.foodiehub.util.AppConstants;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;

public interface ApiHelper {

    ApiHeader getApiHeader();

    @GET(ApiEndPoint.API_RESTAURANT_LIST)
    Observable<RestaurantsResponse> getRestaurants(RestaurantsRequest restaurantsRequest);

    @GET(ApiEndPoint.API_RESTAURANT_DETAIL)
    Observable<RestaurantDetailResponse> getRestaurantDetail(RestaurantDetailRequest restaurantDetailRequest);

    @GET(ApiEndPoint.API_LOCATION_LIST)
    Observable<LocationResponse> getLocations(
            @HeaderMap Map<String, String> apiKey,
            @Query(AppConstants.Params.LATITUDE) Double lat,
            @Query(AppConstants.Params.LONGITUDE) Double lon);
}
