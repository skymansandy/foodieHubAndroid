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

import javax.inject.Inject;

import androidx.annotation.NonNull;
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
import in.codeshuffle.foodiehub.ui.home.restaurantlist.RestaurantListInterface;
import in.codeshuffle.foodiehub.ui.imageviewer.ImageViewerActivity;
import in.codeshuffle.foodiehub.ui.location.LocationActivity;
import in.codeshuffle.foodiehub.ui.restaurantpage.RestaurantDetailActivity;
import in.codeshuffle.foodiehub.util.AppConstants.Params;
import in.codeshuffle.foodiehub.util.CommonUtils;

import static in.codeshuffle.foodiehub.service.LocationService.ACTION_LOCATION_BROADCAST;

public class HomeActivity extends BaseActivity
        implements HomeMvpView, RestaurantListInterface {

    private static final String TAG = HomeActivity.class.getSimpleName();

    @BindView(R.id.restaurantList)
    RecyclerView restaurantList;
    @BindView(R.id.shimmer)
    View shimmer;
    @BindView(R.id.contentLayout)
    View contentLayout;
    @BindView(R.id.nothingFound)
    View nothingFound;
    @BindView(R.id.addressHeader)
    TextView addressHeader;
    @BindView(R.id.locationType)
    TextView locationType;
    @BindView(R.id.addressSubHeader)
    TextView addressSubHeader;
    @BindView(R.id.search_query)
    EditText etSearchRestaurants;
    @BindView(R.id.button_return_to_top)
    View returnToTop;

    @Inject
    HomeMvpPresenter<HomeMvpView> mPresenter;

    @Inject
    PreferencesHelper preferencesHelper;

    @Inject
    RestaurantAdapter restaurantsAdapter;
    @Inject
    LinearLayoutManager restaurantLayoutManager;

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

    //Scroll listener
    private RecyclerView.OnScrollListener restaurantListScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            if (linearLayoutManager == null) return;

            if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                returnToTop.setVisibility(View.GONE);
            } else if (linearLayoutManager.findFirstVisibleItemPosition() > 2) {
                returnToTop.setVisibility(View.VISIBLE);
            }

            //Skip take
            if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == restaurantsAdapter.getItemCount() - 1) {
                restaurantsAdapter.loadMoreRestaurants();
            }
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
                    preferencesHelper.getLongitude(), 0);
        } else {
            addressHeader.setText(getString(R.string.choose_location));
            addressSubHeader.setText(getString(R.string.choose_location_for_handpicked_restaurants));
            mPresenter.fetchRestaurants(etSearchRestaurants.getText().toString(),
                    null, null, 0);
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

        restaurantList.setLayoutManager(restaurantLayoutManager);
        restaurantList.setAdapter(restaurantsAdapter);

        //API call
        if (preferencesHelper.isPreferenceMyLocation()) {
            showRestaurantsNearMe();
        } else {
            showRestaurants();
        }

        returnToTop.setVisibility(View.GONE);
        returnToTop.setOnClickListener(v -> {
            restaurantList.smoothScrollToPosition(0);
            returnToTop.setVisibility(View.GONE);
        });
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
    public void onLoadMoreRestaurants(int skip) {
        mPresenter.fetchRestaurants(etSearchRestaurants.getText().toString(),
                preferencesHelper.getLatitude(),
                preferencesHelper.getLongitude(),
                skip);
    }


    @Override
    public void onRestaurantResponse(RestaurantsResponse restaurantsResponse) {
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
    public void showLoading() {
        restaurantsAdapter.clearRestaurants();
        restaurantList.addOnScrollListener(restaurantListScrollListener);
        contentLayout.setVisibility(View.GONE);
        shimmer.setVisibility(View.VISIBLE);
        nothingFound.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        shimmer.setVisibility(View.GONE);
        contentLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMoreRestaurantResponse(RestaurantsResponse restaurantsResponse) {
        if (restaurantsResponse.getRestaurants().size() == 0) {
            restaurantList.removeOnScrollListener(restaurantListScrollListener);
            restaurantsAdapter.addMoreRestaurants(null);
        } else {
            restaurantsAdapter.addMoreRestaurants(restaurantsResponse.getRestaurants());
            restaurantsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        if (!etSearchRestaurants.getText().toString().equals("")) {
            etSearchRestaurants.setText("");
            etSearchRestaurants.clearFocus();
            mPresenter.fetchRestaurants("", preferencesHelper.getLatitude(),
                    preferencesHelper.getLongitude(), 0);
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
