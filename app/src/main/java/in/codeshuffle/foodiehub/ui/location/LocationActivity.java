package in.codeshuffle.foodiehub.ui.location;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import in.codeshuffle.foodiehub.R;
import in.codeshuffle.foodiehub.data.network.model.LocationResponse;
import in.codeshuffle.foodiehub.ui.base.BaseActivity;

public class LocationActivity extends BaseActivity implements LocationMvpView {

    private static final String TAG = LocationActivity.class.getSimpleName();

    @Inject
    LocationMvpPresenter<LocationMvpView> mPresenter;

    @BindView(R.id.search)
    EditText etSearch;

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
    public void checkLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.LOCATION_HARDWARE)
                == PackageManager.PERMISSION_GRANTED) {
            mPresenter.fetchLocations("");
        } else {

        }
    }

    @Override
    public void onLocationList(LocationResponse locationResponse) {
        Log.d(TAG, "onLocationList: " + locationResponse.toString());
    }
}
