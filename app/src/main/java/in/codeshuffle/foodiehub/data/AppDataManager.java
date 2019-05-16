package in.codeshuffle.foodiehub.data;


import android.content.Context;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.codeshuffle.foodiehub.data.db.DbHelper;
import in.codeshuffle.foodiehub.data.network.ApiHeader;
import in.codeshuffle.foodiehub.data.network.ApiHelper;
import in.codeshuffle.foodiehub.data.network.model.LocationResponse;
import in.codeshuffle.foodiehub.data.network.model.RestaurantDetailRequest;
import in.codeshuffle.foodiehub.data.network.model.RestaurantDetailResponse;
import in.codeshuffle.foodiehub.data.network.model.RestaurantsRequest;
import in.codeshuffle.foodiehub.data.network.model.RestaurantsResponse;
import in.codeshuffle.foodiehub.data.prefs.PreferencesHelper;
import in.codeshuffle.foodiehub.di.ApplicationContext;
import in.codeshuffle.foodiehub.util.AppConstants;
import io.reactivex.Observable;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;


@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public void updateApiHeader(String accessToken) {

    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public Observable<RestaurantsResponse> getRestaurants(RestaurantsRequest restaurantsRequest) {
        return mApiHelper.getRestaurants(restaurantsRequest);
    }

    @Override
    public Observable<RestaurantDetailResponse> getRestaurantDetail(RestaurantDetailRequest restaurantDetailRequest) {
        return mApiHelper.getRestaurantDetail(restaurantDetailRequest);
    }

    @Override
    public Observable<LocationResponse> getLocations(Map<String, String> apiKey,
                                                     Double lat,
                                                     Double lon) {
        return mApiHelper.getLocations(apiKey, lat, lon);
    }
}
