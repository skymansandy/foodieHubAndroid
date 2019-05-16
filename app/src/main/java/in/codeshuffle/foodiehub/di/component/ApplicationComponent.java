package in.codeshuffle.foodiehub.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import in.codeshuffle.foodiehub.FoodieHubApp;
import in.codeshuffle.foodiehub.di.ApplicationContext;
import in.codeshuffle.foodiehub.di.module.ApplicationModule;
import in.codeshuffle.foodiehub.di.module.NetworkModule;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    //inject app
    void inject(FoodieHubApp app);

    @ApplicationContext
    Context context();

    FoodieHubApp application();
}
