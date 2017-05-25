package com.elitesdk.demo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appinfosdk.utils.AppInfoListener;
import com.appinfosdk.utils.CommonObjects;
import com.appinfosdk.utils.ErrorModel;
import com.appinfosdk.utils.SucessModel;
import com.elitesdk.R;

/**
 * Created by CartelTech on 3/21/2017.
 */

@SuppressLint("LongLogTag")
public class CommonActivity4 extends AppCompatActivity implements AppInfoListener {
    String macAdress = "", sha256MacAdress = "", advertisingId = "";
    Button btnNext;
    TextView tvData;
    String strLog = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        tvData = (TextView) findViewById(R.id.tvData);
        tvData.setText("===CommonActivity4===");


        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CommonActivity4.this,CommonActivity3.class));
            }
        });

        //AppInfoSet.setOnInfoListner(this);
        CommonObjects.setActivityCommon(this);

/*

        //AppInfoListener listener = (AppInfoListener) mContext;
        ErrorModel errorModel = new ErrorModel();
        errorModel.exception = new Exception("Location Provider Disabled");

        AppInfoSet appInfoSet = new AppInfoSet();
        appInfoSet.setErrorModel(errorModel);

*/

    }

int i = 0 ;
    @Override
    public void onSuccess(SucessModel sucessModel) {

        Log.i("==onSuccess=",sucessModel.getStatusCode());
        Log.i("==onSuccess=getAdvertisingId",sucessModel.getAdvertisingId());
        Log.i("==onSuccess=getMacAdressId",sucessModel.getMacAdressId());
        Log.i("==onSuccess=getSha256MacAdressId",sucessModel.getSha256MacAdressId());

        Log.i("==onSuccess=getLatitude",""+sucessModel.getLocationLatLong().getLatitude());
        Log.i("==onSuccess=getLongitude",""+sucessModel.getLocationLatLong().getLongitude());

        if(i  > 5)
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
            strLog = strLog + "\n====================" ;
            strLog = strLog + "\n ==getLatitude=="+sucessModel.getLocationLatLong().getLatitude();
            strLog = strLog + "\n ==getLongitude=="+sucessModel.getLocationLatLong().getLongitude();
            strLog = strLog + "\n ==getMacAdressId=="+sucessModel.getMacAdressId();
            strLog = strLog + "\n ==getStatusCode=="+sucessModel.getStatusCode();
            strLog = strLog + "\n====================" ;
            tvData.setText(strLog);
        }
    }

    @Override
    public void onFailure(ErrorModel errorModel) {
        Log.i("==onFailure=getStatusCode",errorModel.getStatusCode());
        Log.i("==onFailure=getMessage",errorModel.getException().getMessage());

        if(i  > 5)
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
            strLog = strLog + "\n====================" ;
            strLog = strLog + "\n ==getException-Msg=="+errorModel.getException().getMessage();
            strLog = strLog + "\n ==getStatusCode=="+errorModel.getStatusCode();
            strLog = strLog + "\n====================" ;
            tvData.setText("========"+errorModel.getStatusCode()+"==============");
        }
    }
}
