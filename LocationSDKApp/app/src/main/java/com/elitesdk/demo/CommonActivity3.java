package com.elitesdk.demo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.appinfosdk.utils.MyLocationService;
import com.elitesdk.R;


/**
 * Created by CartelTech on 3/21/2017.
 */

@SuppressLint("LongLogTag")
public class CommonActivity3 extends AppCompatActivity/* implements AppInfoListener*/ {
    String macAdress = "", sha256MacAdress = "", advertisingId = "";
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setText("GO BACK");
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CommonActivity3.this,CommonActivity.class));
            }
        });

        btnNext.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(),"Stop service",Toast.LENGTH_SHORT).show();
                /***** For start Service  ****/
                Intent myIntent = new Intent(CommonActivity3.this, MyLocationService.class);
                stopService(myIntent);

                return false;
            }
        });



        // calling this function for the getting mac address and Advertising Id.
    }


}
