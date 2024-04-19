package com.example.tuan12_21110940;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtLop, edtNganh;
    Button btnThem, btnXoa, btnSua;
    TextView tvThongBao, tvSoLuong;
    ListView lvDanhSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLop = findViewById(R.id.edtTenLop);
        edtNganh = findViewById(R.id.edtTenNganh);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);

        SQLiteHelperPhong sqLiteHelperPhong = new SQLiteHelperPhong(this);
        sqLiteHelperPhong.deleteAllPhong();
        sqLiteHelperPhong.addPhong(new Phong(1, "18T1", "CNTT"));
        sqLiteHelperPhong.addPhong(new Phong(2, "18T2", "CNTT"));
        sqLiteHelperPhong.addPhong(new Phong(3, "18T3", "CNTT"));

        List<Phong> list = sqLiteHelperPhong.getAllPhong();
        List<String> listString = new ArrayList<>();
        for (Phong phong : list) {
            listString.add(phong.getLop() + " - " + phong.getNganh());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listString);
        lvDanhSach = findViewById(R.id.lvDanhSach);
        lvDanhSach.setAdapter(adapter);

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] arr = listString.get(position).split(" - ");
                edtLop.setText(arr[0]);
                edtNganh.setText(arr[1]);
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lop = edtLop.getText().toString();
                String nganh = edtNganh.getText().toString();
                if (lop.isEmpty() || nganh.isEmpty()) {
                    tvThongBao.setText("Không được để trống");
                    return;
                }
                try {
                    // Lấy id cuối cùng
                    int id = list.size() > 0 ? list.get(list.size() - 1).getId() + 1 : 1;
                    sqLiteHelperPhong.addPhong(new Phong(id, lop, nganh));
                    listString.add(lop + " - " + nganh);
                    adapter.notifyDataSetChanged();
                    edtLop.setText("");
                    edtNganh.setText("");
                }
                catch (Exception e) {
                    tvThongBao.setText("Lỗi: " + e.getMessage());
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lop = edtLop.getText().toString();
                String nganh = edtNganh.getText().toString();
                if (lop.isEmpty() || nganh.isEmpty()) {
                    tvThongBao.setText("Không được để trống");
                    return;
                }
                try {
                    sqLiteHelperPhong.deletePhong(new Phong(lop, nganh));
                    listString.remove(lop + " - " + nganh);
                    adapter.notifyDataSetChanged();
                    edtLop.setText("");
                    edtNganh.setText("");
                }
                catch (Exception e) {
                    tvThongBao.setText("Lỗi: " + e.getMessage());
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lop = edtLop.getText().toString();
                String nganh = edtNganh.getText().toString();
                if (lop.isEmpty() || nganh.isEmpty()) {
                    tvThongBao.setText("Không được để trống");
                    return;
                }
                try {
                    sqLiteHelperPhong.updatePhong(new Phong(lop, nganh));
                    listString.remove(lop + " - " + nganh);
                    listString.add(lop + " - " + nganh);
                    adapter.notifyDataSetChanged();
                    edtLop.setText("");
                    edtNganh.setText("");
                }
                catch (Exception e) {
                    tvThongBao.setText("Lỗi: " + e.getMessage());
                }
            }
        });

    }
}