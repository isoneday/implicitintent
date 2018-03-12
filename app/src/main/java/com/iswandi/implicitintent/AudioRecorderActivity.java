package com.iswandi.implicitintent;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iswandi.implicitintent.helper.MyFunction;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioRecorderActivity extends MyFunction {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.btnPlay)
    Button btnPlay;
    @BindView(R.id.btnRecordStop)
    Button btnRecordStop;

    MediaRecorder recorder;
    String outputfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_recorder);
        ButterKnife.bind(this);
        btnPlay.setEnabled(false);
    }

    @OnClick({R.id.btnPlay, R.id.btnRecordStop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnPlay:
                MediaPlayer player = new MediaPlayer();
                try {
                    player.setDataSource(outputfile);
                    player.prepare();
                    player.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnRecordStop:
                if (btnRecordStop.getText().toString().equalsIgnoreCase("RECORD")){
                    try{
                recorder = new MediaRecorder();
                outputfile = Environment.getExternalStorageDirectory().getAbsolutePath()
                        +"/REC"+currentDate()+".3gp";
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                recorder.setOutputFile(outputfile);
                recorder.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    recorder.start();
                    btnRecordStop.setText("STOP");
                }
                else  if (btnRecordStop.getText().toString().equalsIgnoreCase("STOP"))
                {
                    recorder.stop();
                    recorder.release();
                    recorder=null;
                    btnPlay.setEnabled(true);
                    btnRecordStop.setText("RECORD");
                }
                break;

        }
    }
}
