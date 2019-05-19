package in.codeshuffle.foodiehub.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import in.codeshuffle.foodiehub.R;
import in.codeshuffle.foodiehub.ui.base.BaseActivity;
import in.codeshuffle.foodiehub.ui.home.HomeActivity;

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
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

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
        Intent toSplash = HomeActivity.getStartIntent(this);
        startActivity(toSplash);
        finish();
    }
}
