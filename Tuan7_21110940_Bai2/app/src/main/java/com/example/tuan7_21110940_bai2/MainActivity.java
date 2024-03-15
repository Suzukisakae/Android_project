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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.btnGo);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Bundle b = new Bundle();
                b.putString("sTen", "Le Thanh Vinh");
                intent.putExtra("bundleExtra", b);
                intent.putExtra("sMSSV", "21110940");
                intent.putExtra("sDanhGia", "Okie");
                intent.putExtra("iTuoi", 20);
                intent.putExtra("bGioiTinh", true);
                startActivity(intent);
            }
        });
    }
}