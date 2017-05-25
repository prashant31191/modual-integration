package com.appinfosdk.utils;

/**
 * Created by prashant.patel on 5/25/2017.
 */
import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;

public class MyLocationService extends Service
{
    private static final String TAG = "==MyLocationService==";
    private Context mContext;
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 1000;
    private static final float LOCATION_DISTANCE = 0f;
    private String advertisingId = "";



    private class LocationListener implements android.location.LocationListener
    {
        Location mLastLocation;

        public LocationListener(String provider)
        {
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
         //   mLastLocation = new Location(LocationManager.GPS_PROVIDER);
        }

        @Override
        public void onLocationChanged(Location location)
        {
            try {
                Log.e(TAG, "onLocationChanged: " + location);
                mLastLocation.set(location);

                SucessModel sucessModel = new SucessModel();

                sucessModel.locationLatLong = location;
                sucessModel.advertisingId = advertisingId;
                // get mac adress from your device
                sucessModel.macAdressId = MacAdressId.getMacAddr();
                // get Mac Adress Id encrypt to SHA256
                sucessModel.sha256MacAdressId = MacAdressId.encryptSHA256(sucessModel.macAdressId);
                sucessModel.wifiSSID = MacAdressId.getWifiSSIDN(mContext);

                AppInfoSet appInfoSet = new AppInfoSet();
                appInfoSet.setSucessModel(sucessModel);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onProviderDisabled(String provider)
        {
            Log.e(TAG, "onProviderDisabled: " + provider);
            // on error

            //AppInfoListener listener = (AppInfoListener) mContext;
            ErrorModel errorModel = new ErrorModel();
            errorModel.exception = new Exception("Location Provider Disabled");

            AppInfoSet appInfoSet = new AppInfoSet();
            appInfoSet.setErrorModel(errorModel);

            //listener.onFailure(errorModel);
        }

        @Override
        public void onProviderEnabled(String provider)
        {
            Log.e(TAG, "onProviderEnabled: " + provider);
            // on sucess
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
            Log.e(TAG, "onStatusChanged: " + provider);
        }
    }

    LocationListener[] mLocationListeners = new LocationListener[] {
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.e(TAG, "onStartCommand");
        mContext = getApplicationContext();
        getAdvertisingId();

        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate()
    {
        Log.e(TAG, "onCreate");
        mContext = getApplicationContext();
        boolean isLocation = isLocationServicesAvailable(mContext);
        Log.e(TAG, "==isLocationServicesAvailable===isLocation=="+isLocation);
        initializeLocationManager();
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
        } catch (SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }
    }

    @Override
    public void onDestroy()
    {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
    }

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }

    private void getAdvertisingId()
    {

        // for the get Advertising Id
        new Thread(new Runnable() {
            public void run() {
                try {
                    AdvertisingIdClient.AdInfo adInfo = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext());
                    // get AdvertisingId
                    advertisingId = adInfo.getId();
                    Log.i("===", "====advertisingId==="+advertisingId);

                /*    // pass "sha256MacAdress" variable for the encrypted sha256 mac adress
                    Log.i("MAC SHA526", sha256MacAdress);

                    // pass "advertisingId" variable for the Advertising Id
                    Log.i("AdvertisingId", advertisingId);


*/
                     /*
                    set here api or webserice call

                    link like = "http://www.appurlxxxxxxx.com/appnameXXX/apiname.php?macaddr="+sha256MacAdress+"&adsId="+advertisingId

                        macaddr = Request parameter using for get Mac adress in your api
                        adsId = Request parameter using get Advertising id in your api

                    */

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    public static boolean isLocationServicesAvailable(Context context) {
        int locationMode = 0;
        String locationProviders;
        boolean isAvailable = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }

            isAvailable = (locationMode != Settings.Secure.LOCATION_MODE_OFF);
        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            isAvailable = !TextUtils.isEmpty(locationProviders);
        }

        boolean coarsePermissionCheck = (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        boolean finePermissionCheck = (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);

        return isAvailable && (coarsePermissionCheck || finePermissionCheck);
    }
}