package in.codeshuffle.foodiehub.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import in.codeshuffle.foodiehub.FoodieHubApp;
import in.codeshuffle.foodiehub.di.ApplicationContext;
import in.codeshuffle.foodiehub.di.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(FoodieHubApp app);

    @ApplicationContext
    Context context();

    Application application();
}
