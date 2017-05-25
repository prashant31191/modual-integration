package com.elitesdk.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.appinfosdk.utils.MacAdressId;
import com.appinfosdk.utils.MyLocationService;
import com.elitesdk.R;


/**
 * Created by CartelTech on 3/21/2017.
 */

@SuppressLint("LongLogTag")
public class CommonActivity extends Activity /*implements AppInfoListener*/ {
    String macAdress = "", sha256MacAdress = "", advertisingId = "";
    Button btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CommonActivity.this,CommonActivity2.class));
            }
        });

        /***** For start Service  ****/
        Intent myIntent = new Intent(CommonActivity.this, MyLocationService.class);
        startService(myIntent);
    }
}
