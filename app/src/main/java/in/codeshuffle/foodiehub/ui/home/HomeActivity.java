package in.codeshuffle.foodiehub.ui.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.codeshuffle.foodiehub.R;
import in.codeshuffle.foodiehub.data.network.model.RestaurantsResponse;
import in.codeshuffle.foodiehub.data.prefs.PreferencesHelper;
import in.codeshuffle.foodiehub.ui.base.BaseActivity;
import in.codeshuffle.foodiehub.ui.home.restaurantlist.RestaurantAdapter;
import in.codeshuffle.foodiehub.ui.imageviewer.ImageViewerActivity;
import in.codeshuffle.foodiehub.ui.location.LocationActivity;
import in.codeshuffle.foodiehub.ui.restaurantpage.RestaurantDetailActivity;
import in.codeshuffle.foodiehub.util.AppConstants.Params;
import in.codeshuffle.foodiehub.util.CommonUtils;

import static in.codeshuffle.foodiehub.service.LocationService.ACTION_LOCATION_BROADCAST;

public class HomeActivity extends BaseActivity
        implements HomeMvpView, RestaurantAdapter.RestaurantListInterface {

    @Inject
    HomeMvpPresenter<HomeMvpView> mPresenter;

    @Inject
    PreferencesHelper preferencesHelper;

    @BindView(R.id.restaurantList)
    RecyclerView restaurantList;
    @BindView(R.id.homeShimmer)
    View homeShimmer;
    @BindView(R.id.contentLayout)
    View contentLayout;
    @BindView(R.id.nothingFound)
    View nothingFound;
    @BindView(R.id.location)
    View location;
    @BindView(R.id.addressHeader)
    TextView addressHeader;
    @BindView(R.id.locationType)
    TextView locationType;
    @BindView(R.id.addressSubHeader)
    TextView addressSubHeader;
    @BindView(R.id.search_query)
    EditText etSearchRestaurants;

    private RestaurantAdapter restaurantsAdapter;

    private BroadcastReceiver locationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            mPresenter.saveMyLocation(intent.getDoubleExtra(Params.LATITUDE, 0.0f),
                    intent.getDoubleExtra(Params.LONGITUDE, 0.0f),
                    intent.getStringExtra(Params.CITY),
                    intent.getStringExtra(Params.STREET));
            showRestaurants();

            //Stop service and broadcast
            stopLocationService();
            LocalBroadcastManager.getInstance(HomeActivity.this).unregisterReceiver(this);
        }
    };

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        Bundle extras = new Bundle();
        intent.putExtras(extras);
        return intent;
    }

    public static Intent getLocationBroadcastIntent(Double lat, Double lng, String cityName, String streetName) {
        Intent intent = new Intent(ACTION_LOCATION_BROADCAST);
        intent.putExtra(Params.CITY, cityName);
        intent.putExtra(Params.STREET, streetName);
        intent.putExtra(Params.LATITUDE, lat);
        intent.putExtra(Params.LONGITUDE, lng);
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
    public void startLocationBroadcastReceiver() {
        LocalBroadcastManager.getInstance(this).registerReceiver(
                locationReceiver, new IntentFilter(ACTION_LOCATION_BROADCAST)
        );
    }

    @Override
    public void stopLocationBroadcastReceiver() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(locationReceiver);
    }

    @Override
    public void locationPermissionGranted() {
        CommonUtils.showShortToast(this, getString(R.string.showing_restaurants_around_you));
        startLocationService();
    }

    @Override
    public void locationPermissionNotGranted() {
        mPresenter.clearLocationPreference();
        CommonUtils.showShortToast(this, getString(R.string.showing_restaurants_over_the_world));
        showRestaurants();
    }

    @Override
    public void showRestaurants() {
        locationType.setText(getString(preferencesHelper.isPreferenceMyLocation() ?
                R.string.your_location : R.string.custom_location));

        if (preferencesHelper.getLatitude() != 0 && preferencesHelper.getLongitude() != 0) {
            addressHeader.setText(preferencesHelper.getCity());
            addressSubHeader.setText(preferencesHelper.getLocality());
            mPresenter.fetchRestaurants(etSearchRestaurants.getText().toString(),
                    preferencesHelper.getLatitude(),
                    preferencesHelper.getLongitude());
        } else {
            addressHeader.setText(getString(R.string.choose_location));
            addressSubHeader.setText(getString(R.string.choose_location_for_handpicked_restaurants));
            mPresenter.fetchRestaurants(etSearchRestaurants.getText().toString(),
                    null, null);
        }
    }

    @Override
    protected void setUp() {
        etSearchRestaurants.setHint(getString(R.string.search_restaurants));

        etSearchRestaurants.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard();
                showRestaurants();
                return true;
            }

            return false;
        });

        restaurantsAdapter = new RestaurantAdapter(this, this, new ArrayList<>());
        restaurantList.setLayoutManager(new LinearLayoutManager(this));
        restaurantList.setAdapter(restaurantsAdapter);

        if (preferencesHelper.isPreferenceMyLocation()) {
            fetchCurrentLocation();
        } else {
            showRestaurants();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationBroadcastReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationBroadcastReceiver();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        hideKeyboard();
        showRestaurants();
    }


    @OnClick({R.id.location})
    void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.location:
                startActivity(LocationActivity.getStartIntent(this));
                break;
        }
    }

    @Override
    public void onOpenRestaurantDetail(String restaurantId) {
        startActivity(RestaurantDetailActivity.getStartIntent(this, restaurantId));
    }

    @Override
    public void onImagePreviewClicked(String restaurantId, String imageUrl, String restaurantName, String restaurantThumb) {
        startActivity(ImageViewerActivity.getStartIntent(this,
                imageUrl,
                restaurantName,
                restaurantThumb));
    }

    @Override
    public void onSeeAllPreview(String imagesUrl) {
        CustomTabsIntent customTabsIntent = CommonUtils.getChromeCustomTab(R.color.colorPrimary);
        customTabsIntent.launchUrl(this, Uri.parse(imagesUrl));
    }


    @Override
    public void showLoading() {
        contentLayout.setVisibility(View.GONE);
        homeShimmer.setVisibility(View.VISIBLE);
        nothingFound.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        homeShimmer.setVisibility(View.GONE);
        contentLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRestaurantList(RestaurantsResponse restaurantsResponse) {
        restaurantsAdapter.clearRestaurants();
        if (restaurantsResponse.getRestaurants().size() == 0) {
            nothingFound.setVisibility(View.VISIBLE);
            contentLayout.setVisibility(View.GONE);
        } else {
            nothingFound.setVisibility(View.GONE);
            contentLayout.setVisibility(View.VISIBLE);
            restaurantsAdapter.addRestaurants(restaurantsResponse.getRestaurants());
        }
    }

    @Override
    public void onBackPressed() {
        if (!etSearchRestaurants.getText().toString().equals("")) {
            etSearchRestaurants.setText("");
            etSearchRestaurants.clearFocus();
            mPresenter.fetchRestaurants("", preferencesHelper.getLatitude(),
                    preferencesHelper.getLongitude());
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        stopLocationService();
        super.onDestroy();
    }
}
