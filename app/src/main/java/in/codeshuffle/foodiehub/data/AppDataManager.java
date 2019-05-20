package in.codeshuffle.foodiehub.data;


import android.content.Context;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.codeshuffle.foodiehub.data.db.DbHelper;
import in.codeshuffle.foodiehub.data.network.ApiClient;
import in.codeshuffle.foodiehub.data.network.ApiHeader;
import in.codeshuffle.foodiehub.data.network.model.LocationResponse;
import in.codeshuffle.foodiehub.data.network.model.RestaurantDetailResponse;
import in.codeshuffle.foodiehub.data.network.model.RestaurantsResponse;
import in.codeshuffle.foodiehub.data.prefs.PreferencesHelper;
import in.codeshuffle.foodiehub.di.ApplicationContext;
import io.reactivex.Observable;


@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiClient mApiClient;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper,
                          ApiClient apiClient) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiClient = apiClient;
    }

    @Override
    public void updateApiHeader(String accessToken) {

    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiClient.getApiHeader();
    }

    @Override
    public Observable<RestaurantsResponse> getRestaurants(Map<String, String> apiKey, String query, Double lat, Double lon, int skip) {
        return mApiClient.getRestaurants(apiKey, query, lat, lon, skip);
    }

    @Override
    public Observable<RestaurantDetailResponse> getRestaurantDetail(
            Map<String, String> apiKey, String restaurantId) {
        return mApiClient.getRestaurantDetail(apiKey, restaurantId);
    }

    @Override
    public Observable<LocationResponse> getLocations(
            Map<String, String> apiKey, String query, Double lat, Double lon, int count) {
        return mApiClient.getLocations(apiKey, query, lat, lon, count);
    }

    @Override
    public String getLocality() {
        return mPreferencesHelper.getLocality();
    }

    @Override
    public void setLocality(String locality) {
        mPreferencesHelper.setLocality(locality);
    }

    @Override
    public Double getLatitude() {
        return mPreferencesHelper.getLatitude();
    }

    @Override
    public void setLatitude(Double latitude) {
        mPreferencesHelper.setLatitude(latitude);
    }

    @Override
    public Double getLongitude() {
        return mPreferencesHelper.getLongitude();
    }

    @Override
    public void setLongitude(Double longitude) {
        mPreferencesHelper.setLongitude(longitude);
    }

    @Override
    public String getCity() {
        return mPreferencesHelper.getCity();
    }

    @Override
    public void setCity(String city) {
        mPreferencesHelper.setCity(city);
    }

    @Override
    public boolean isPreferenceMyLocation() {
        return mPreferencesHelper.isPreferenceMyLocation();
    }

    @Override
    public void setPreferenceMyLocation(boolean isMyLocation) {
        mPreferencesHelper.setPreferenceMyLocation(isMyLocation);
    }

    @Override
    public void saveLocationInfo(boolean isMyLocation, Double latitude, Double longitude, String city, String locality) {
        mPreferencesHelper.saveLocationInfo(isMyLocation, latitude,
                longitude, city, locality);
    }
}
