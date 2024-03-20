/*
* NGười viết: Lê Thành Vinh
* MSSV: 21110940
* VD1: Gửi tin nhắn
* */

package com.example.tuan7_21110940_vd1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
import android.telephony.SmsManager;

public class MainActivity extends AppCompatActivity {
    EditText edtPhone, edtNoidung;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPhone = findViewById(R.id.edtPhone);
        edtNoidung = findViewById(R.id.edtNoiDung);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    sendSMS();
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.SEND_SMS}, 100);
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            sendSMS();
        } else {
            Toast.makeText(this, "Quyền gửi tin nhắn bị từ chối", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSMS() {
        String phone = edtPhone.getText().toString();
        String noidung = edtNoidung.getText().toString();
        if (phone.isEmpty() || noidung.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;

        }
        else {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phone, null, noidung, null, null);
            Toast.makeText(this, "Đã gửi tin nhắn", Toast.LENGTH_SHORT).show();
        }
    }
}