package com.iswandi.implicitintent.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.iswandi.implicitintent.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BrowserActivity extends AppCompatActivity {

    @BindView(R.id.btnakseslink)
    Button btnakseslink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnakseslink)
    public void onViewClicked() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.linkurl))));

    }
}
