package in.codeshuffle.foodiehub.data;


import in.codeshuffle.foodiehub.data.db.DbHelper;
import in.codeshuffle.foodiehub.data.network.ApiHelper;
import in.codeshuffle.foodiehub.data.prefs.PreferencesHelper;



public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

    void updateApiHeader(String accessToken);
}
