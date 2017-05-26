package com.elitesdk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appinfosdk.utils.AppInfoListener;
import com.appinfosdk.utils.CommonObjects;
import com.appinfosdk.utils.ErrorModel;
import com.appinfosdk.utils.MyLocationService;
import com.appinfosdk.utils.SucessModel;
import com.elitesdk.demo.CommonActivity;
import com.elitesdk.demo.CommonActivity2;
import com.elitesdk.demo.CommonActivity4;

public class HomeActivity extends AppCompatActivity implements AppInfoListener {

    String strLog = "";
    Button btnNext;
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tvData = (TextView) findViewById(R.id.tvData);
        tvData.setText("===HomeActivity===");


        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setVisibility(View.GONE);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,CommonActivity4.class));
            }
        });

        CommonObjects.setActivityCommon(this);


        /***** For start Service  ****/
        Intent myIntent = new Intent(HomeActivity.this, MyLocationService.class);
        startService(myIntent);


    }


    int i = 0 ;
    @Override
    public void onSuccess(SucessModel sucessModel) {

        /*Log.i("==onSuccess=",sucessModel.getStatusCode());
        Log.i("==onSuccess=getAdvertisingId",sucessModel.getAdvertisingId());
        Log.i("==onSuccess=getMacAdressId",sucessModel.getMacAdressId());
        Log.i("==onSuccess=getSha256MacAdressId",sucessModel.getSha256MacAdressId());

        Log.i("==onSuccess=getLatitude",""+sucessModel.getLocationLatLong().getLatitude());
        Log.i("==onSuccess=getLongitude",""+sucessModel.getLocationLatLong().getLongitude());
*/
        if(i  > 50)
        {
            strLog = "";
            i=0;
        }
        else
        {
            i = i+1;
        }


        if(tvData !=null)
        {
            strLog = strLog + "\n" ;
            strLog = strLog + "\n Latitude : "+sucessModel.getLocationLatLong().getLatitude();
            strLog = strLog + "\n Longitude : " +sucessModel.getLocationLatLong().getLongitude();
            strLog = strLog + "\n AdvertisingId : "+sucessModel.getAdvertisingId();
            strLog = strLog + "\n MacAdressId : "+sucessModel.getMacAdressId();
            strLog = strLog + "\n SHA256-MacAdressId : "+sucessModel.getSha256MacAdressId();
            strLog = strLog + "\n SSID : "+sucessModel.getWifiSSID();
            //strLog = strLog + "\n ==getStatusCode=="+sucessModel.getStatusCode();
            //strLog = strLog + "\n" ;
            tvData.setText(strLog);
        }
    }

    @Override
    public void onFailure(ErrorModel errorModel) {
     //   Log.i("==onFailure=getStatusCode",errorModel.getStatusCode());
     //   Log.i("==onFailure=getMessage",errorModel.getException().getMessage());

        if(i  > 50)
        {
            strLog = "";
            i=0;
        }
        else
        {
            i = i+1;
        }


        if(tvData !=null)
        {
            strLog = strLog + "\n" ;
            strLog = strLog + "\n Error Message : "+errorModel.getException().getMessage();
          //  strLog = strLog + "\n ==getStatusCode=="+errorModel.getStatusCode();
         //   strLog = strLog + "\n" ;

            tvData.setText(strLog);
        }
    }
}
