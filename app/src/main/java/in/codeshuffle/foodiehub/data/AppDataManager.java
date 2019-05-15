package in.codeshuffle.foodiehub.data;


import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.codeshuffle.foodiehub.data.db.DbHelper;
import in.codeshuffle.foodiehub.data.network.ApiHeader;
import in.codeshuffle.foodiehub.data.network.ApiHelper;
import in.codeshuffle.foodiehub.data.prefs.PreferencesHelper;
import in.codeshuffle.foodiehub.di.ApplicationContext;



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
}
