package com.iswandi.implicitintent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.iswandi.implicitintent.R;
import com.iswandi.implicitintent.helper.MyFunction;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmailActivity extends MyFunction {

    @BindView(R.id.edtto)
    EditText edtto;
    @BindView(R.id.edtsubject)
    EditText edtsubject;
    @BindView(R.id.edtmessage)
    EditText edtmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        String to = edtto.getText().toString();
        String message =edtmessage.getText().toString();
        String subject =edtsubject.getText().toString();

        if (id==R.id.mn_send){
            if (TextUtils.isEmpty(to)){
                edtto.setError("tujuan tidak boleh kosong");
                edtto.requestFocus();
                myAnimation(edtto);
            }else if (TextUtils.isEmpty(message)){
                edtmessage.setError("pesan tidak boleh kosong");
                edtmessage.requestFocus();
                myAnimation(edtmessage);
            }else if (TextUtils.isEmpty(subject)){
                edtsubject.setError("subject tidak boleh kosong");
                edtsubject.requestFocus();
                myAnimation(edtsubject);
            }else {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                intent.putExtra(Intent.EXTRA_TEXT, message);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);

                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "silahkan pilih email client"));
            }}
        else{
            edtto.setText("");
            edtmessage.setText("");
            edtsubject.setText("");
        }
        return super.onOptionsItemSelected(item);
    }
}
