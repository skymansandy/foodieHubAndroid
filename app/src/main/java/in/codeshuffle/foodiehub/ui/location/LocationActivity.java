package in.codeshuffle.foodiehub.ui.location;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.Nullable;
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
import in.codeshuffle.foodiehub.util.CommonUtils;

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
        preferencesHelper.setLatitude(latitude);
        preferencesHelper.setLongitude(longitude);
        preferencesHelper.setCity(city);
        preferencesHelper.setLocality(locality);
        CommonUtils.showShortToast(this, String.format("%s%s",
                getString(R.string.location_pref_change), city));
        finish();
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
