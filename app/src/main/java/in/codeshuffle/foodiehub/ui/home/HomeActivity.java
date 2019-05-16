package in.codeshuffle.foodiehub.ui.home;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.codeshuffle.foodiehub.LocationService;
import in.codeshuffle.foodiehub.R;
import in.codeshuffle.foodiehub.data.network.model.RestaurantsResponse;
import in.codeshuffle.foodiehub.data.prefs.PreferencesHelper;
import in.codeshuffle.foodiehub.ui.base.BaseActivity;
import in.codeshuffle.foodiehub.ui.home.restaurantlist.RestaurantAdapter;
import in.codeshuffle.foodiehub.ui.location.LocationActivity;
import in.codeshuffle.foodiehub.ui.restaurantpage.RestaurantDetailActivity;
import in.codeshuffle.foodiehub.util.AppConstants.Params;
import in.codeshuffle.foodiehub.util.CommonUtils;
import in.codeshuffle.foodiehub.util.NetworkUtils;

public class HomeActivity extends BaseActivity implements HomeMvpView, RestaurantAdapter.RestaurantListInterface {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private static final int PERMISSION_REQUEST_CODE = 100;

    @Inject
    HomeMvpPresenter<HomeMvpView> mPresenter;

    @Inject
    PreferencesHelper preferencesHelper;

    @BindView(R.id.restaurantList)
    RecyclerView restaurantList;
    @BindView(R.id.location)
    View location;

    private RestaurantAdapter restaurantsAdapter;

    private boolean mAlreadyStartedService = false;


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
    public void setupLocationService() {
        if (isGooglePlayServicesAvailable()) {
            checkPermissionAndStartService();
        } else {
            CommonUtils.showShortToast(this, getString(R.string.no_google_play));
        }
    }

    /**
     * Show A Dialog with button to refresh the internet state.
     */
    private void checkPermissionAndStartService() {
        if (NetworkUtils.isNetworkConnected(HomeActivity.this)) {
            if (NetworkUtils.isLocationPermissionsGiven(HomeActivity.this)) {
                startLocationService();
                mPresenter.fetchRestaurantsNearMe(preferencesHelper.getLatitude(),
                        preferencesHelper.getLongitude());
            } else {
                requestLocationPermissions();
                mPresenter.fetchRestaurantsNearMe(null, null);
            }
        } else {
            internetPrompt();
        }
    }

    private void internetPrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle(getString(R.string.no_internet));
        builder.setMessage(getString(R.string.no_internet));
        builder.setPositiveButton(getString(R.string.refresh),
                (dialog, which) -> {
                    //TODO ask internet
                });
        builder.setNegativeButton(getString(R.string.proceed),
                (dialog, which) -> dialog.dismiss());
        builder.setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Step 3: Start the Location Monitor Service
     */
    private void startLocationService() {
        if (!mAlreadyStartedService) {
            Intent intent = new Intent(this, LocationService.class);
            startService(intent);
            mAlreadyStartedService = true;
        }
    }

    /**
     * Return the availability of GooglePlayServices
     */
    public boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(this, status, 2404).show();
            }
            return false;
        }
        return true;
    }


    private void requestLocationPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

        boolean shouldProvideRationale2 =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION);

        if (shouldProvideRationale || shouldProvideRationale2) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");
            showSnackBar("Need location permission");
        } else {
            Log.i(TAG, "Requesting permission");
            ActivityCompat.requestPermissions(HomeActivity.this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    protected void setUp() {
        restaurantsAdapter = new RestaurantAdapter(this, this, new ArrayList<>());
        restaurantList.setLayoutManager(new LinearLayoutManager(this));
        restaurantList.setAdapter(restaurantsAdapter);

        LocalBroadcastManager.getInstance(this).registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        Double latitude = intent.getDoubleExtra(Params.LATITUDE, 0.0);
                        Double longitude = intent.getDoubleExtra(Params.LONGITUDE, 0.0);
                        preferencesHelper.setLatitude(latitude);
                        preferencesHelper.setLongitude(longitude);
                        stopLocationService();
                    }
                }, new IntentFilter(LocationService.ACTION_LOCATION_BROADCAST)
        );
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        stopLocationService();
        super.onDestroy();
    }

    @OnClick({R.id.location})
    void onViewClicked(View view){
        switch (view.getId()){
            case R.id.location:
                startActivity(LocationActivity.getStartIntent(this));
                break;
        }
    }

    private void stopLocationService() {
        stopService(new Intent(this, LocationService.class));
        mAlreadyStartedService = false;
    }

    @Override
    public void onRestaurantList(RestaurantsResponse restaurantsResponse) {
        restaurantsAdapter.addRestaurants(restaurantsResponse.getRestaurants());
    }

    @Override
    public void onOpenRestaurantDetail(String restaurantId) {
        startActivity(RestaurantDetailActivity.getStartIntent(this, restaurantId));
    }
}
