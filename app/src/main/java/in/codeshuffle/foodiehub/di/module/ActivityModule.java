package in.codeshuffle.foodiehub.di.module;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Module;
import dagger.Provides;
import in.codeshuffle.foodiehub.BuildConfig;
import in.codeshuffle.foodiehub.di.PerActivity;
import in.codeshuffle.foodiehub.ui.home.HomeMvpPresenter;
import in.codeshuffle.foodiehub.ui.home.HomeMvpView;
import in.codeshuffle.foodiehub.ui.home.HomePresenter;
import in.codeshuffle.foodiehub.ui.location.LocationMvpPresenter;
import in.codeshuffle.foodiehub.ui.location.LocationMvpView;
import in.codeshuffle.foodiehub.ui.location.LocationPresenter;
import in.codeshuffle.foodiehub.ui.splash.SplashMvpPresenter;
import in.codeshuffle.foodiehub.ui.splash.SplashMvpView;
import in.codeshuffle.foodiehub.ui.splash.SplashPresenter;
import in.codeshuffle.foodiehub.util.AppConstants;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    HomeMvpPresenter<HomeMvpView> provideHomePresenter(
            HomePresenter<HomeMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LocationMvpPresenter<LocationMvpView> provideLocationPresenter(
            LocationPresenter<LocationMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    Map<String, String> providerDefaultHeaders(){
        Map<String, String> apiHeaders = new HashMap<>();
        apiHeaders.put(AppConstants.Params.USER_KEY, BuildConfig.API_KEY);
        return apiHeaders;
    }
}
