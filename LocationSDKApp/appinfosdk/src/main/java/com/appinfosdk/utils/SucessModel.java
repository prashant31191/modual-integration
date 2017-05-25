package com.appinfosdk.utils;

import android.location.Location;

/**
 * Created by prashant.patel on 5/25/2017.
 */

public class SucessModel
{
    public Location locationLatLong;
    public String advertisingId="";
    public String macAdressId="";
    public String wifiSSID="";
    public String sha256MacAdressId="";
    public String statusCode="200";


    public String getMacAdressId() {
        return macAdressId;
    }

    public String getAdvertisingId() {
        return advertisingId;
    }

    public Location getLocationLatLong() {
        return locationLatLong;
    }

    public String getSha256MacAdressId() {
        return sha256MacAdressId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getWifiSSID() {
        return wifiSSID;
    }
}
