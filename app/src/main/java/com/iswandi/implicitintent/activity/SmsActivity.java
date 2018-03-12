package com.iswandi.implicitintent.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iswandi.implicitintent.R;
import com.iswandi.implicitintent.helper.MyFunction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SmsActivity extends MyFunction {

    private static final int REQUESTSMS = 1;
    @BindView(R.id.edt)
    EditText edtnohp;
    @BindView(R.id.edtmessage)
    EditText edtmessage;
    @BindView(R.id.btnsmsintent)
    Button btnsmsintent;
    @BindView(R.id.btnkirimsms)
    Button btnkirimsms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.edt, R.id.btnsmsintent, R.id.btnkirimsms})
    public void onViewClicked(View view) {
        String nohp = edtnohp.getText().toString();
        String pesan = edtmessage.getText().toString();
        switch (view.getId()) {
            case R.id.edt:

                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(i, 1);

                break;
            case R.id.btnsmsintent:
                if (TextUtils.isEmpty(nohp)) {
                    edtnohp.setError("tidak boleh kosong");
                    edtnohp.requestFocus();
                    myAnimation(edtnohp);
                } else if (TextUtils.isEmpty(pesan)) {
                    edtmessage.setError("tidak boleh kosong");
                    edtmessage.requestFocus();
                    myAnimation(edtmessage);
                } else {
                    Intent sms = new Intent(Intent.ACTION_VIEW);
                    sms.putExtra("address", nohp);
                    sms.putExtra("sms_body", pesan);
                    sms.setType("vnd.android-dir/mms-sms");
                    startActivity(sms);
                }
                break;
            case R.id.btnkirimsms:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.SEND_SMS)) {
                    } else {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.SEND_SMS},
                                REQUESTSMS);
                    }
                }else {
                    try {
                        SmsManager manager = SmsManager.getDefault();
                        manager.sendTextMessage(nohp,null,pesan,null,null);
                        Toast.makeText(c, "berhasil mengirim sms", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(c, "gagal mengirim sms"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }

    }


    //untuk menangkap response dari startActivityForResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Cursor cursor = null;
            try {
                Uri uri = data.getData();
                cursor = getContentResolver().query(uri, new String[]{
                        ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
                if (cursor != null && cursor.moveToNext()) {
                    String phone = cursor.getString(0);
                    edtnohp.setText(phone);
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        } }
}
