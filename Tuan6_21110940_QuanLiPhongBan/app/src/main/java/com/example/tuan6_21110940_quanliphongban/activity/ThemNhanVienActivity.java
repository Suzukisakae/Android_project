package com.example.tuan6_21110940_quanliphongban.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.tuan6_21110940_quanliphongban.R;
import com.example.tuan6_21110940_quanliphongban.modal.ChucVu;
import com.example.tuan6_21110940_quanliphongban.modal.NhanVien;

public class ThemNhanVienActivity extends AppCompatActivity {
    private Button btnXoaTrang, btnLuuNhanVien;
    private EditText editManv, editTenNv;
    private RadioButton radNam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_vien);
        getFormWidgets();
        addEvents();
    }
    public void getFormWidgets(){
        btnXoaTrang = (Button) findViewById(R.id.btnxoatrang);
        btnLuuNhanVien = (Button) findViewById(R.id.btnluunv);
        editManv = (EditText) findViewById(R.id.editMaNV);
        editTenNv = (EditText) findViewById(R.id.editTenNV);
        radNam = (RadioButton) findViewById(R.id.radNam);
    }
    public void addEvents() {
        btnXoaTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doXoaTrang();
            }
        });
        btnLuuNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLuuNhanVien();
            }
        });
    }
    public void doXoaTrang(){
        editManv.setText("");
        editTenNv.setText("");
        editManv.requestFocus();
    }
    public void doLuuNhanVien(){
        NhanVien nv = new NhanVien();
        nv.setMa(editManv.getText()+"");
        nv.setTen(editTenNv.getText()+"");
        nv.setChucVu(ChucVu.NhanVien);
        nv.setGioiTinh((!radNam.isChecked()));
        Intent i = getIntent();
        Bundle b = new Bundle();
        b.putSerializable("NHANVIEN", nv);
        i.putExtra("DATA", b);
        setResult(MainActivity.THEM_NHAN_VIEN_THANHCONG, i);
        finish();
    }
}