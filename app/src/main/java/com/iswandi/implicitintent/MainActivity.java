package com.iswandi.implicitintent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iswandi.implicitintent.activity.AlarmActivity;
import com.iswandi.implicitintent.activity.AudioManagerActivity;
import com.iswandi.implicitintent.activity.BluetoothActivity;
import com.iswandi.implicitintent.activity.BrowserActivity;
import com.iswandi.implicitintent.activity.CallPhoneActivity;
import com.iswandi.implicitintent.activity.CameraActivity;
import com.iswandi.implicitintent.activity.EmailActivity;
import com.iswandi.implicitintent.activity.SmsActivity;
import com.iswandi.implicitintent.activity.TTSActivity;
import com.iswandi.implicitintent.activity.VideoActivity;
import com.iswandi.implicitintent.activity.WifiActivity;
import com.iswandi.implicitintent.helper.MyFunction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MyFunction {

    @BindView(R.id.btnaudiomanager)
    Button btnaudiomanager;
    @BindView(R.id.btnaudiorecorder)
    Button btnaudiorecorder;
    @BindView(R.id.btnbluetooth)
    Button btnbluetooth;
    @BindView(R.id.btnbrowser)
    Button btnbrowser;
    @BindView(R.id.btncamera)
    Button btncamera;
    @BindView(R.id.btnemail)
    Button btnemail;
    @BindView(R.id.btnphone)
    Button btnphone;
    @BindView(R.id.btnsms)
    Button btnsms;
    @BindView(R.id.btntts)
    Button btntts;
    @BindView(R.id.btnwifi)
    Button btnwifi;
    @BindView(R.id.btnvideo)
    Button btnvideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnaudiomanager, R.id.btnaudiorecorder, R.id.btnbluetooth, R.id.btnbrowser, R.id.btncamera, R.id.btnemail, R.id.btnphone, R.id.btnsms, R.id.btntts, R.id.btnwifi, R.id.btnvideo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnaudiomanager:
                pindahclass(AudioManagerActivity.class);
                break;
            case R.id.btnaudiorecorder:
                pindahclass(com.iswandi.implicitintent.AudioRecorderActivity.class);

                break;
            case R.id.btnbluetooth:
                pindahclass(BluetoothActivity.class);

                break;
            case R.id.btnbrowser:
                pindahclass(BrowserActivity.class);

                break;
            case R.id.btncamera:
                pindahclass(CameraActivity.class);

                break;
            case R.id.btnemail:
                pindahclass(EmailActivity.class);

                break;
            case R.id.btnphone:
                pindahclass(CallPhoneActivity.class);

                break;
            case R.id.btnsms:
                pindahclass(SmsActivity.class);
                break;
            case R.id.btntts:
                pindahclass(TTSActivity.class);
                break;
            case R.id.btnwifi:
                pindahclass(WifiActivity.class);
                break;
            case R.id.btnvideo:
                pindahclass(VideoActivity.class);
                break;
        }
    }

    public void onAlarm(View view) {
        pindahclass(AlarmActivity.class);

    }
}
