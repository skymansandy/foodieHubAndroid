package in.codeshuffle.foodiehub.ui.splash;

import android.os.Handler;

import javax.inject.Inject;

import in.codeshuffle.foodiehub.data.network.ApiClient;
import in.codeshuffle.foodiehub.data.network.ApiHeader;
import in.codeshuffle.foodiehub.ui.base.BasePresenter;

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V>
        implements SplashMvpPresenter<V> {

    private static final long SPLASH_DURATION_MILLIS = 2000;

    @Inject
    SplashPresenter(ApiClient apiClient, ApiHeader apiHeader) {
        super(apiClient, apiHeader);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        takeToHomeScreen();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void handleApiError() {
        super.handleApiError();
    }

    private void takeToHomeScreen() {
        new Handler().postDelayed(() -> {
            if (!isViewAttached())
                return;
            getMvpView().openHomeScreen();
        }, SPLASH_DURATION_MILLIS);
    }
}
