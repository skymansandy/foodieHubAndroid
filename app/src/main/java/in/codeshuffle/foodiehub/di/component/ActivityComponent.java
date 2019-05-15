package in.codeshuffle.foodiehub.di.component;

import dagger.Component;
import in.codeshuffle.foodiehub.di.PerActivity;
import in.codeshuffle.foodiehub.di.module.ActivityModule;
import in.codeshuffle.foodiehub.ui.home.HomeActivity;
import in.codeshuffle.foodiehub.ui.splash.SplashActivity;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity splashActivity);

    void inject(HomeActivity homeActivity);

}
