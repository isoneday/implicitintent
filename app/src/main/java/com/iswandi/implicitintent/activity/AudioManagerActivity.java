package com.iswandi.implicitintent.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;

import com.iswandi.implicitintent.R;
import com.iswandi.implicitintent.helper.MyFunction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioManagerActivity extends MyFunction {

    @BindView(R.id.ring)
    Button ring;
    @BindView(R.id.vibrate)
    Button vibrate;
    @BindView(R.id.silent)
    Button silent;
    AudioManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_manager);
        ButterKnife.bind(this);
        manager= (AudioManager)getSystemService(c.AUDIO_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && checkSelfPermission(Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        10);
            }
            return;
        }
    }

    @OnClick({R.id.ring, R.id.vibrate, R.id.silent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ring:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //nougat
                    manager.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_UNMUTE, 0);
                } else {

                    manager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    mytoast("dalam mode normal");
                }
                break;
            case R.id.vibrate:
                manager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                break;
            case R.id.silent:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //nougat
                    manager.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_MUTE, 0);
                } else {
                    manager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    mytoast("dalam mode silent");
                }
                break;
        }
    }
}
