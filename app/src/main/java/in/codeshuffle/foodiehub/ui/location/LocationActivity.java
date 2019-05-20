package in.codeshuffle.foodiehub.ui.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.codeshuffle.foodiehub.R;
import in.codeshuffle.foodiehub.data.network.model.LocationResponse;
import in.codeshuffle.foodiehub.data.prefs.PreferencesHelper;
import in.codeshuffle.foodiehub.ui.base.BaseActivity;
import in.codeshuffle.foodiehub.ui.location.locationlist.LocationAdapter;
import in.codeshuffle.foodiehub.util.AppConstants;
import in.codeshuffle.foodiehub.util.CommonUtils;
import in.codeshuffle.foodiehub.util.NetworkUtils;

import static in.codeshuffle.foodiehub.service.LocationService.ACTION_LOCATION_BROADCAST;

public class LocationActivity extends BaseActivity implements LocationMvpView, LocationAdapter.LocationListInterface {

    private static final String TAG = LocationActivity.class.getSimpleName();

    @Inject
    LocationMvpPresenter<LocationMvpView> mPresenter;

    @Inject
    PreferencesHelper preferencesHelper;

    @BindView(R.id.locationsRecycler)
    RecyclerView locationsRecycler;
    @BindView(R.id.search_query)
    EditText etSearchLocations;
    @BindView(R.id.useMyLocation)
    TextView useMyLocation;

    private LocationAdapter locationsAdapter;

    BroadcastReceiver locationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            mPresenter.saveLocationInfo(true,
                    intent.getDoubleExtra(AppConstants.Params.LATITUDE, 0.0f),
                    intent.getDoubleExtra(AppConstants.Params.LONGITUDE, 0.0f),
                    intent.getStringExtra(AppConstants.Params.CITY),
                    intent.getStringExtra(AppConstants.Params.STREET));

            //Stop service and broadcast
            stopLocationService();
            LocalBroadcastManager.getInstance(LocationActivity.this).unregisterReceiver(this);

            finish();
        }
    };


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LocationActivity.class);
        Bundle extras = new Bundle();
        intent.putExtras(extras);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_pick);

        setUnBinder(ButterKnife.bind(this));

        getActivityComponent().inject(LocationActivity.this);

        mPresenter.onAttach(LocationActivity.this);

        setUp();
    }

    @Override
    protected void setUp() {
        etSearchLocations.setHint(getString(R.string.search_for_locations));

        etSearchLocations.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mPresenter.fetchLocations(etSearchLocations.getText().toString(),
                        preferencesHelper.getLatitude(),
                        preferencesHelper.getLongitude());
                hideKeyboard();
                return true;
            }

            return false;
        });

        locationsAdapter = new LocationAdapter(this, this, new ArrayList<>());
        locationsRecycler.setLayoutManager(new LinearLayoutManager(this));
        locationsRecycler.setAdapter(locationsAdapter);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (!etSearchLocations.getText().toString().equals("")) {
            etSearchLocations.setText("");
            etSearchLocations.clearFocus();
            mPresenter.fetchLocations("", preferencesHelper.getLatitude(),
                    preferencesHelper.getLongitude());
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onLocationList(LocationResponse locationResponse) {
        Log.d(TAG, "onLocationList: " + locationResponse.toString());
        locationsAdapter.clearLocations();
        locationsAdapter.addLocations(locationResponse.getLocationSuggestions());
        locationsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLocationSelected(String city, String locality, Double latitude, Double longitude) {
        mPresenter.saveLocationInfo(false, latitude, longitude, city, locality);
        CommonUtils.showShortToast(this, String.format("%s%s",
                getString(R.string.location_pref_change), city));
        finish();
    }

    @OnClick(R.id.useMyLocation)
    void onUseCurrentLocation() {
        if (NetworkUtils.isLocationPermissionsGiven(this)) {
            getAndStoreCurrentLocation();
        } else {
            requestLocationPermissionsWithRationale();
        }
    }

    @Override
    public void locationPermissionGranted() {
        getAndStoreCurrentLocation();
    }

    @Override
    public void startLocationBroadcastReceiver() {
        LocalBroadcastManager.getInstance(this).registerReceiver(
                locationReceiver, new IntentFilter(ACTION_LOCATION_BROADCAST)
        );
    }

    @Override
    public void stopLocationBroadcastReceiver() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(
                locationReceiver);
    }

    @Override
    public void locationPermissionNotGranted() {
        CommonUtils.showShortToast(this, getString(R.string.cant_access_current_location_without_perm));
    }

    private void getAndStoreCurrentLocation() {
        startLocationService();
        useMyLocation.setText(getString(R.string.detecting_location));
    }

    @OnClick(R.id.back)
    void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                mPresenter.onBackPressed();
                break;
        }
    }
}
