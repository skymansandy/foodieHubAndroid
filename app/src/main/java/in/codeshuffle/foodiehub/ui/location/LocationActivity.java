package in.codeshuffle.foodiehub.ui.location;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import in.codeshuffle.foodiehub.R;
import in.codeshuffle.foodiehub.data.network.model.LocationResponse;
import in.codeshuffle.foodiehub.ui.base.BaseActivity;
import in.codeshuffle.foodiehub.ui.home.restaurantlist.RestaurantAdapter;
import in.codeshuffle.foodiehub.ui.location.locationlist.LocationAdapter;
import in.codeshuffle.foodiehub.util.CommonUtils;

public class LocationActivity extends BaseActivity implements LocationMvpView, LocationAdapter.LocationListInterface {

    private static final String TAG = LocationActivity.class.getSimpleName();

    @Inject
    LocationMvpPresenter<LocationMvpView> mPresenter;

    @BindView(R.id.search)
    EditText etSearch;
    @BindView(R.id.locations)
    RecyclerView locationsList;

    private LocationAdapter locationsAdapter;

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
        locationsAdapter = new LocationAdapter(this, this, new ArrayList<>());
        locationsList.setLayoutManager(new LinearLayoutManager(this));
        locationsList.setAdapter(locationsAdapter);


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.fetchLocations(s.toString());
            }
        });
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onLocationList(LocationResponse locationResponse) {
        Log.d(TAG, "onLocationList: " + locationResponse.toString());
        locationsAdapter.addLocation(locationResponse.getLocationSuggestions());
    }

    @Override
    public void onLocationSelected(String city, Double latitude, Double longitude) {
        CommonUtils.showShortToast(this, city);
    }
}
