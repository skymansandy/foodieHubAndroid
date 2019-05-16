package in.codeshuffle.foodiehub.data;


import in.codeshuffle.foodiehub.data.db.DbHelper;
import in.codeshuffle.foodiehub.data.network.ApiClient;
import in.codeshuffle.foodiehub.data.prefs.PreferencesHelper;



public interface DataManager extends DbHelper, PreferencesHelper, ApiClient {

    void updateApiHeader(String accessToken);
}
