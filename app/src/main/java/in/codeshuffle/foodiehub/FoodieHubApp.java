package in.codeshuffle.foodiehub;

import android.app.Application;

import java.io.File;

import in.codeshuffle.foodiehub.di.component.ApplicationComponent;
import in.codeshuffle.foodiehub.di.component.DaggerApplicationComponent;
import in.codeshuffle.foodiehub.di.module.ApplicationModule;
import in.codeshuffle.foodiehub.util.AppLogger;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class FoodieHubApp extends Application {

    private ApplicationComponent mApplicationComponent;

    public void onCreate() {
        super.onCreate();

        File cacheFile = new File(getCacheDir(), "response");

        //App component
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);

        //Font
       /* CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );*/

        //Logger
        AppLogger.init();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}