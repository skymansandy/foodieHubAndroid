package in.codeshuffle.foodiehub.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import in.codeshuffle.foodiehub.FoodieHubApp;
import in.codeshuffle.foodiehub.data.DataManager;
import in.codeshuffle.foodiehub.data.db.DbHelper;
import in.codeshuffle.foodiehub.data.network.ApiClient;
import in.codeshuffle.foodiehub.data.network.ApiHeader;
import in.codeshuffle.foodiehub.data.prefs.PreferencesHelper;
import in.codeshuffle.foodiehub.di.ActivityContext;
import in.codeshuffle.foodiehub.di.ApplicationContext;
import in.codeshuffle.foodiehub.di.module.ApplicationModule;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    //inject app
    void inject(FoodieHubApp app);

    @ApplicationContext
    Context applicationContext();

    FoodieHubApp application();

    DataManager dataManager();

    ApiClient apiHelper();

    DbHelper dbHelper();

    ApiHeader apiHeader();

    PreferencesHelper preferenceHelper();
}
