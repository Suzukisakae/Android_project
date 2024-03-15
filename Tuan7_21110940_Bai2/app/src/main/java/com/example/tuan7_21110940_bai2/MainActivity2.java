/*
 * Nguoi viet: Le Thanh Vinh
 * MSSV: 21110940
 * Mo ta: Bai 2
 * */
package com.example.tuan7_21110940_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView t = findViewById(R.id.tvThongTin);
        Button b = findViewById(R.id.btnGoBack);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundleExtra");
        String sTen = bundle.getString("sTen");
        String sMSSV = intent.getStringExtra("sMSSV");
        String sDanhGia = intent.getStringExtra("sDanhGia");
        int iTuoi = intent.getIntExtra("iTuoi", 0);
        boolean bGioiTinh = intent.getBooleanExtra("bGioiTinh", false);
        String str;
        if (bGioiTinh) {
            str = "Nam";
        } else {
            str = "Nu";
        }
        t.setText("Ten: " + sTen + "\nMSSV: " + sMSSV + "\nDanh gia: " + sDanhGia + "\nTuoi: " + iTuoi + "\nGioi tinh: " + str);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(MainActivity2.this, MainActivity.class);
                c.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(c);
            }
        });

    }
}