package in.codeshuffle.foodiehub.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import butterknife.ButterKnife;
import in.codeshuffle.foodiehub.R;
import in.codeshuffle.foodiehub.ui.base.BaseActivity;

public class HomeActivity extends BaseActivity implements HomeMvpView {

    @Inject
    HomeMvpPresenter<HomeMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        Bundle extras = new Bundle();
        intent.putExtras(extras);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setUnBinder(ButterKnife.bind(this));

        getActivityComponent().inject(HomeActivity.this);

        mPresenter.onAttach(HomeActivity.this);

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
    public void checkLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.LOCATION_HARDWARE)
                == PackageManager.PERMISSION_GRANTED) {
            mPresenter.fetchRestaurantsNearMe();
        } else {

        }
    }
}
