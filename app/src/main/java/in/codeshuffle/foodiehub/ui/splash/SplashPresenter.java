package in.codeshuffle.foodiehub.ui.splash;

import android.os.Handler;

import javax.inject.Inject;

import in.codeshuffle.foodiehub.ui.base.BasePresenter;

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V>
        implements SplashMvpPresenter<V> {

    private static final long SPLASH_DURATION_MILLIS = 2000;

    @Inject
    SplashPresenter() {
        super();
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
        new Handler().postDelayed(()
                -> getMvpView().openHomeScreen(), SPLASH_DURATION_MILLIS);
    }
}
