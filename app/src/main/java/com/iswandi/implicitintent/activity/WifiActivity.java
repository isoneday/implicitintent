package com.iswandi.implicitintent.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.iswandi.implicitintent.R;
import com.iswandi.implicitintent.helper.MyFunction;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WifiActivity extends MyFunction {

    @BindView(R.id.wifi)
    Switch wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        ButterKnife.bind(this);
        wifi.setChecked(status());
        wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                wifichangestatus(isChecked);
            }
        });
    }

    private void wifichangestatus(boolean b) {
        WifiManager manager = (WifiManager)getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.CHANGE_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE)
                    != PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.CHANGE_WIFI_STATE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        new String[]{Manifest.permission.ACCESS_WIFI_STATE,
                                Manifest.permission.CHANGE_WIFI_STATE},
                        110);


            }
            return;
        }
        else if (b==true&& !manager.isWifiEnabled()){
            mytoast("wifi aktif");
            manager.setWifiEnabled(true);
        }else if (b==false& manager.isWifiEnabled()){
            mytoast("wifi tidak aktif");
            manager.setWifiEnabled(false);
        }
    }

    private boolean status() {

        WifiManager manager = (WifiManager)getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        return manager.isWifiEnabled();
    }
}
