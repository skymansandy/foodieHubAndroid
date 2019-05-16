package in.codeshuffle.foodiehub.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.codeshuffle.foodiehub.di.ApplicationContext;
import in.codeshuffle.foodiehub.di.PreferenceInfo;


@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                         @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public Double getLatitude() {
        return (double) mPrefs.getFloat(LATITUDE, 0.0f);
    }

    public void setLatitude(Double latitude) {
        mEditor = mPrefs.edit();
        mEditor.putFloat(LATITUDE, latitude.floatValue());
        mEditor.apply();
    }

    @Override
    public Double getLongitude() {
        return (double) mPrefs.getFloat(LONGITUDE, 0.0f);
    }

    @Override
    public void setLongitude(Double longitude) {
        mEditor = mPrefs.edit();
        mEditor.putFloat(LONGITUDE, longitude.floatValue());
        mEditor.apply();
    }
}
