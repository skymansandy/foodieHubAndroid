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


}
