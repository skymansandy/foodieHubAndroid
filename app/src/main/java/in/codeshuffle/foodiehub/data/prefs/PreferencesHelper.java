package in.codeshuffle.foodiehub.data.prefs;



public interface PreferencesHelper {

    void setLatitude(Double latitude);

    void setLongitude(Double longitude);

    String getLocality();

    void setLocality(String locality);

    Double getLatitude();

    Double getLongitude();

    String getCity();

    void setCity(String city);

    boolean isPreferenceMyLocation();

    void setPreferenceMyLocation(boolean isMyLocation);

    void saveLocationInfo(boolean isMyLocation, Double latitude, Double longitude, String city, String locality);
}
