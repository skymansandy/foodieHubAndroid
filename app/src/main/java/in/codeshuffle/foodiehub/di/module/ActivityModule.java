package in.codeshuffle.foodiehub.di.module;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Module;
import dagger.Provides;
import in.codeshuffle.foodiehub.di.PerActivity;
import in.codeshuffle.foodiehub.ui.home.HomeMvpPresenter;
import in.codeshuffle.foodiehub.ui.home.HomeMvpView;
import in.codeshuffle.foodiehub.ui.home.HomePresenter;
import in.codeshuffle.foodiehub.ui.imageviewer.ImageViewerMvpPresenter;
import in.codeshuffle.foodiehub.ui.imageviewer.ImageViewerMvpView;
import in.codeshuffle.foodiehub.ui.imageviewer.ImageViewerPresenter;
import in.codeshuffle.foodiehub.ui.location.LocationMvpPresenter;
import in.codeshuffle.foodiehub.ui.location.LocationMvpView;
import in.codeshuffle.foodiehub.ui.location.LocationPresenter;
import in.codeshuffle.foodiehub.ui.restaurantpage.RestaurantDetailMvpPresenter;
import in.codeshuffle.foodiehub.ui.restaurantpage.RestaurantDetailMvpView;
import in.codeshuffle.foodiehub.ui.restaurantpage.RestaurantDetailPresenter;
import in.codeshuffle.foodiehub.ui.splash.SplashMvpPresenter;
import in.codeshuffle.foodiehub.ui.splash.SplashMvpView;
import in.codeshuffle.foodiehub.ui.splash.SplashPresenter;

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
    RestaurantDetailMvpPresenter<RestaurantDetailMvpView> provideRestaurantDetailPresenter(
            RestaurantDetailPresenter<RestaurantDetailMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ImageViewerMvpPresenter<ImageViewerMvpView> provideImageViewerPresenter(
            ImageViewerPresenter<ImageViewerMvpView> presenter) {
        return presenter;
    }
}
