package in.codeshuffle.foodiehub.data.network;


import in.codeshuffle.foodiehub.data.network.model.LocationRequest;
import in.codeshuffle.foodiehub.data.network.model.LocationResponse;
import in.codeshuffle.foodiehub.data.network.model.RestaurantDetailRequest;
import in.codeshuffle.foodiehub.data.network.model.RestaurantDetailResponse;
import in.codeshuffle.foodiehub.data.network.model.RestaurantsRequest;
import in.codeshuffle.foodiehub.data.network.model.RestaurantsResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiHelper {

    ApiHeader getApiHeader();

    @GET(ApiEndPoint.API_RESTAURANT_LIST)
    Observable<RestaurantsResponse> getRestaurants(RestaurantsRequest restaurantsRequest);

    @GET(ApiEndPoint.API_RESTAURANT_DETAIL)
    Observable<RestaurantDetailResponse> getRestaurantDetail(RestaurantDetailRequest restaurantDetailRequest);

    @GET(ApiEndPoint.API_LOCATION_LIST)
    Observable<LocationResponse> getLocations(LocationRequest locationRequest);
}
