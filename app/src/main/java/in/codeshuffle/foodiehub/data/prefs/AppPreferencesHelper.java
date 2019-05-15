package in.codeshuffle.foodiehub.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.codeshuffle.foodiehub.di.ApplicationContext;
import in.codeshuffle.foodiehub.di.PreferenceInfo;


@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }
}
