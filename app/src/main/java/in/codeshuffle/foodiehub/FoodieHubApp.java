package in.codeshuffle.foodiehub;

import android.app.Application;

import in.codeshuffle.foodiehub.di.component.ApplicationComponent;
import in.codeshuffle.foodiehub.di.component.DaggerApplicationComponent;
import in.codeshuffle.foodiehub.di.module.ApplicationModule;
import in.codeshuffle.foodiehub.util.AppLogger;

public class FoodieHubApp extends Application {

    private ApplicationComponent mApplicationComponent;

    public void onCreate() {
        super.onCreate();

        //DI
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        //Logger
        AppLogger.init();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}