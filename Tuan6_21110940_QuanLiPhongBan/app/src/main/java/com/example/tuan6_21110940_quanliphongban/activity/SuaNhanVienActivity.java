package com.example.tuan6_21110940_quanliphongban.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tuan6_21110940_quanliphongban.modal.NhanVien;
import com.example.tuan6_21110940_quanliphongban.R;

public class SuaNhanVienActivity extends Activity {
    EditText editMa, editTen;
    RadioButton radNam;
    Button btnClear, btnSave;
    NhanVien nv = null;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_vien);
        getFormWidgets();
        getDefaultData();
        addEvents();
    }
    public void getFormWidgets(){
        editMa = (EditText) findViewById(R.id.editMaNV);
        editTen = (EditText) findViewById(R.id.editTenNV);
        editMa.setEnabled(false);
        editTen.requestFocus();
        radNam = (RadioButton) findViewById(R.id.radNam);
        btnClear = (Button) findViewById(R.id.btnxoatrang);
        btnSave = (Button) findViewById(R.id.btnluunv);
    }
    public void getDefaultData() {
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("DATA");
        nv = (NhanVien) b.getSerializable("NHANVIEN");
        editMa.setText(nv.getMa());
        editTen.setText(nv.getTen());
        radNam.setChecked(true);
        if (nv.isGioiTinh())
            radNam.setChecked(false);
    }
    public void addEvents() {
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTen.setText("");
                editTen.requestFocus();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                nv.setTen(editTen.getText()+"");
                nv.setGioiTinh(!radNam.isChecked());
                Bundle b = new Bundle();
                b.putSerializable("NHANVIEN", nv);
                i.putExtra("DATA", b);
                setResult(MainActivity.SUA_NHAN_VIEN_THANHCONG, i);
                finish();
            }
        });
    }
}
