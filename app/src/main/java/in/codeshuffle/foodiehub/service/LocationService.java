package in.codeshuffle.foodiehub.service;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import in.codeshuffle.foodiehub.util.AppConstants;
import in.codeshuffle.foodiehub.util.AppConstants.Params;

public class LocationService extends Service implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    public static final String ACTION_LOCATION_BROADCAST = LocationService.class.getName() + "LocationBroadcast";

    private static final String TAG = LocationService.class.getSimpleName();

    private GoogleApiClient mLocationClient;
    private LocationRequest mLocationRequest = new LocationRequest();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mLocationClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationRequest.setInterval(AppConstants.LOCATION_INTERVAL);
        mLocationRequest.setFastestInterval(AppConstants.FASTEST_LOCATION_INTERVAL);

        int priority = LocationRequest.PRIORITY_HIGH_ACCURACY; //by default
        //PRIORITY_BALANCED_POWER_ACCURACY, PRIORITY_LOW_POWER, PRIORITY_NO_POWER are the other priority modes


        mLocationRequest.setPriority(priority);
        mLocationClient.connect();

        //Make it stick to the notification panel so it is less prone to get cancelled by the Operating System.
        return START_STICKY;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "Connected to Google API");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mLocationClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Connection suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "Failed to connect to Google API");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Location changed");

        if (location != null) {
            sendMessageToUI(location.getLatitude(), location.getLongitude());
            //Stop service once location is fetched
            LocationServices.FusedLocationApi.removeLocationUpdates(mLocationClient, this);
            Log.d(TAG, "Disconnected from location updates");

            //Stop service
            stopSelf();
        }
    }

    private void sendMessageToUI(Double lat, Double lng) {
        Log.d(TAG, String.format("Sending info and closing service => Latitude: %s. Longitude: %s", lat, lng));
        Intent intent = new Intent(ACTION_LOCATION_BROADCAST);
        intent.putExtra(Params.LATITUDE, lat);
        intent.putExtra(Params.LONGITUDE, lng);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
