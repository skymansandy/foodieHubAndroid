package in.codeshuffle.foodiehub.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import in.codeshuffle.foodiehub.R;
import in.codeshuffle.foodiehub.ui.base.BaseActivity;
import in.codeshuffle.foodiehub.ui.home.HomeActivity;
import in.codeshuffle.foodiehub.ui.location.LocationActivity;
import in.codeshuffle.foodiehub.ui.restaurantpage.RestaurantDetailActivity;

public class SplashActivity extends BaseActivity implements SplashMvpView {

    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        Bundle extras = new Bundle();
        intent.putExtras(extras);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setUnBinder(ButterKnife.bind(this));

        getActivityComponent().inject(SplashActivity.this);

        mPresenter.onAttach(SplashActivity.this);

        setUp();
    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void openHomeScreen() {
        Intent toSplash = RestaurantDetailActivity.getStartIntent(this);
        startActivity(toSplash);
        finish();
    }
}
