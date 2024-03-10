package com.example.tuan6_21110940_quanliphongban.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.tuan6_21110940_quanliphongban.R;
import com.example.tuan6_21110940_quanliphongban.modal.NhanVien;
import com.example.tuan6_21110940_quanliphongban.modal.PhongBan;

import java.util.ArrayList;

public class ChuyenPhongBanActivity extends AppCompatActivity {
    ListView lvPb;
    private static ArrayList<PhongBan> arrPhongBan = null;
    ArrayAdapter<PhongBan> adapter;
    ImageButton btnApply;
    NhanVien nv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyen_phong_ban);

        getFormWidgets();
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("DATA");
        nv = (NhanVien) b.getSerializable("NHANVIEN");
    }
    public void getFormWidgets() {
        lvPb = (ListView) findViewById(R.id.lvphongban);
        btnApply = (ImageButton) findViewById(R.id.imapply);
        arrPhongBan = MainActivity.getListPhongBan();
        adapter = new ArrayAdapter<PhongBan>(this, android.R.layout.simple_list_item_single_choice, arrPhongBan);
        lvPb.setAdapter(adapter);
        lvPb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            boolean somethingChecked = false;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (somethingChecked) {
                    CheckedTextView cv = (CheckedTextView) view;
                    cv.setChecked(false);
                }
                CheckedTextView cv = (CheckedTextView) view;
                if (!cv.isChecked()) {
                    cv.setChecked(true);
                    arrPhongBan.get(position);
                }
                somethingChecked = true;
            }
        });
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doApply();
            }
        });
    }
    public void doApply() {
        setResult(MainActivity.CHUYEN_PHONG_THANHCONG);
        finish();
    }
}