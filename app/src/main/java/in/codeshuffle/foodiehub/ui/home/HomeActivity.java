package in.codeshuffle.foodiehub.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import in.codeshuffle.foodiehub.R;
import in.codeshuffle.foodiehub.data.db.model.Restaurant;
import in.codeshuffle.foodiehub.data.network.model.LocationResponse;
import in.codeshuffle.foodiehub.ui.base.BaseActivity;
import in.codeshuffle.foodiehub.ui.home.restaurantlist.RestaurantAdapter;
import in.codeshuffle.foodiehub.util.AppLogger;

public class HomeActivity extends BaseActivity implements HomeMvpView {

    @Inject
    HomeMvpPresenter<HomeMvpView> mPresenter;

    @BindView(R.id.restaurantList)
    RecyclerView restaurantList;

    private RestaurantAdapter restaurantsAdapter;

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
        restaurantsAdapter = new RestaurantAdapter(this, new ArrayList<>());
        restaurantList.setLayoutManager(new LinearLayoutManager(this));
        restaurantList.setAdapter(restaurantsAdapter);
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
        mPresenter.fetchRestaurantsNearMe();

    }

    @Override
    public void showRestaurants(List<Restaurant> restaurants) {
        mPresenter.showRestaurantsOnView(restaurants);
    }

    @Override
    public void showMoreRestaurants(List<Restaurant> restaurants) {
        mPresenter.showMoreRestaurantsOnView(restaurants);
    }

    @Override
    public void onLocationList(LocationResponse locationResponse) {
        AppLogger.d("API", locationResponse.toString());
    }
}
