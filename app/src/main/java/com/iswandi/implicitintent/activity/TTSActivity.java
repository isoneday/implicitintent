package com.iswandi.implicitintent.activity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.iswandi.implicitintent.R;
import com.iswandi.implicitintent.helper.MyFunction;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TTSActivity extends MyFunction implements TextToSpeech.OnInitListener {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.btnSpeech)
    Button btnSpeech;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);
        ButterKnife.bind(this);
    tts = new TextToSpeech(c,this);
    }

    @OnClick(R.id.btnSpeech)
    public void onViewClicked() {
        String text = editText.getText().toString();
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    public void onInit(int status) {
        if (status==TextToSpeech.SUCCESS){
            Locale indo = new Locale("id","ID");
            int hasil =tts.setLanguage(Locale.JAPANESE);
            if (hasil==TextToSpeech.LANG_MISSING_DATA||hasil==TextToSpeech.LANG_NOT_SUPPORTED){
                mytoast("bahasa tidak mendukung");
            }else{
                onViewClicked();
                btnSpeech.setEnabled(true);
            }
        }else{
            mytoast("TTS Tidak support");
        }
    }
}
