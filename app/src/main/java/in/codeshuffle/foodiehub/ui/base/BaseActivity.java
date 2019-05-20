package in.codeshuffle.foodiehub.ui.base;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.Unbinder;
import in.codeshuffle.foodiehub.FoodieHubApp;
import in.codeshuffle.foodiehub.R;
import in.codeshuffle.foodiehub.di.component.ActivityComponent;
import in.codeshuffle.foodiehub.di.component.DaggerActivityComponent;
import in.codeshuffle.foodiehub.di.module.ActivityModule;
import in.codeshuffle.foodiehub.service.LocationService;
import in.codeshuffle.foodiehub.util.CommonUtils;
import in.codeshuffle.foodiehub.util.KeyboardUtils;
import in.codeshuffle.foodiehub.util.NetworkUtils;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity implements MvpView,
        BaseFragment.Callback {

    private static final int PERMISSION_REQUEST_CODE = 100;

    private Unbinder mUnBinder;

    private ActivityComponent mActivityComponent;

    private ProgressDialog mProgressDialog;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((FoodieHubApp) getApplication()).getApplicationComponent())
                .build();
    }

    @Override
    public void setupLocationService() {
        fetchCurrentLocation();
    }

    public void showRestaurants() {
    }

    public void fetchCurrentLocation() {
        if (NetworkUtils.isNetworkConnected(this)) {
            if (NetworkUtils.isLocationPermissionsGiven(this)) {
                startLocationService();
            } else {
                requestLocationPermissionsWithRationale();
            }
        } else {
            showNoInternetPrompt();
        }
    }

    private void showNoInternetPrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.no_internet));
        builder.setMessage(getString(R.string.an_internet_connection_is_required));
        builder.setPositiveButton(getString(R.string.restart), ((dialog, which) -> {
            try {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName("com.android.phone", "com.android.phone.NetworkSetting");
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void requestLocationPermissionsWithRationale() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

        boolean shouldProvideRationale2 =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION);

        if (shouldProvideRationale || shouldProvideRationale2) {
            showLocationRationale();
        } else {
            requestLocationPermissions();
        }
    }

    public void requestLocationPermissions(){
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                PERMISSION_REQUEST_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (NetworkUtils.isLocationPermissionsGiven(this)) {
            locationPermissionGranted();
        } else {
            locationPermissionNotGranted();
        }
    }

    public void locationPermissionNotGranted() {
    }

    public void locationPermissionGranted() {
    }

    private void showLocationRationale() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.location_permission_needed_title));
        builder.setMessage(getString(R.string.location_permission_needed));
        builder.setPositiveButton(getString(R.string.grant), ((dialog, which)
                -> requestLocationPermissions()));
        builder.setNegativeButton(getString(R.string.cancel), ((dialog, which) -> {
            locationPermissionNotGranted();
            dialog.dismiss();
        }));
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onError(@StringRes int errorStrResId) {
        onError(getString(errorStrResId));
    }

    @Override
    public void onError(String errorMsg) {
        if (errorMsg != null) {
            showSnackBar(errorMsg);
        } else {
            showSnackBar(getString(R.string.something_went_wrong));
        }
    }

    @Override
    public void showShortToast(String message) {
        CommonUtils.showShortToast(this, message);
    }

    @Override
    public void showShortToast(@StringRes int msgResId) {
        CommonUtils.showShortToast(this, getString(msgResId));
    }

    @Override
    public void showLongToast(String message) {
        CommonUtils.showLongToast(this, message);
    }

    @Override
    public void showLongToast(@StringRes int msgResId) {
        CommonUtils.showLongToast(this, getString(msgResId));
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = sbView
                .findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }

    @Override
    public void showSnackBar(@StringRes int msgResId) {
        showSnackBar(getString(msgResId));
    }

    @Override
    public boolean isNetWorkConnected() {
        return NetworkUtils.isNetworkConnected(this);
    }

    @Override
    public void hideKeyboard() {
        KeyboardUtils.hideSoftInput(this);
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    public void startLocationService() {
        startLocationBroadcastReceiver();
        Intent intent = new Intent(this, LocationService.class);
        startService(intent);
    }

    public void startLocationBroadcastReceiver() {

    }

    public void stopLocationBroadcastReceiver() {

    }

    public void stopLocationService() {
        stopLocationBroadcastReceiver();
        stopService(new Intent(this, LocationService.class));
    }

    protected abstract void setUp();
}
