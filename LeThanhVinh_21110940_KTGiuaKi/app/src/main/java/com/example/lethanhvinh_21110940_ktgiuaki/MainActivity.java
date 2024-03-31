/*
* Nguoi viet: Le Thanh Vinh
* MSSV: 21110940
* Description: Kiem Tra Giua Ki
* */

package com.example.lethanhvinh_21110940_ktgiuaki;

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

public class MainActivity extends AppCompatActivity {
    EditText edtMaSV, edtTenSV;
    Button btnThem, btnXoa, btnSua;
    TextView tvThongBao, tvSoLuong;
    ListView lvDanhSach;
    ArrayList<SinhVien> arrSinhVien = new ArrayList<>();
    ArrayAdapter<SinhVien> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtMaSV = findViewById(R.id.edtID);
        edtTenSV = findViewById(R.id.edtTen);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        tvThongBao = findViewById(R.id.tvThongBao);
        tvSoLuong = findViewById(R.id.tvSoLuong);

        lvDanhSach = findViewById(R.id.lvDanhSach);
        adapter = new SinhVienAdapter(this, R.layout.item_listview, arrSinhVien);
        arrSinhVien.add(new SinhVien("1", "Nguyen Van A"));
        arrSinhVien.add(new SinhVien("2", "Nguyen Van B"));
        arrSinhVien.add(new SinhVien("3", "Nguyen Van C"));
        lvDanhSach.setAdapter(adapter);

        tvSoLuong.setText("Số lượng sinh viên: " + getSoLuongSV());

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maSV = edtMaSV.getText().toString();
                String tenSV = edtTenSV.getText().toString();
                if (maSV.equals("") || tenSV.equals("")) {
                    tvThongBao.setText("Vui lòng nhập đầy đủ thông tin");
                    return;
                }
                if (checkMaSV(maSV)) {
                    tvThongBao.setText("Mã sinh viên đã tồn tại");
                    return;
                }
                arrSinhVien.add(new SinhVien(maSV, tenSV));
                adapter.notifyDataSetChanged();
                tvSoLuong.setText("Số lượng sinh viên: " + getSoLuongSV());
            }
        });
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SinhVien sv = arrSinhVien.get(position);
                edtMaSV.setText(sv.getMaSV());
                edtTenSV.setText(sv.getTenSV());
            }
        });
        // Xử lý sự kiện xóa sinh viên
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maSV = edtMaSV.getText().toString();
                if (maSV.equals("")) {
                    tvThongBao.setText("Vui lòng chọn sinh viên cần xóa");
                    return;
                }
                for (SinhVien sv : arrSinhVien) {
                    if (sv.getMaSV().equals(maSV)) {
                        arrSinhVien.remove(sv);
                        adapter.notifyDataSetChanged();
                        edtMaSV.setText("");
                        edtTenSV.setText("");
                        tvThongBao.setText("Xóa sinh viên thành công");
                        tvSoLuong.setText("Số lượng sinh viên: " + getSoLuongSV());
                        return;
                    }
                }
            }
        });
        // Xử lý sự kiện sửa sinh viên
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maSV = edtMaSV.getText().toString();
                String tenSV = edtTenSV.getText().toString();
                if (maSV.equals("") || tenSV.equals("")) {
                    tvThongBao.setText("Vui lòng chọn sinh viên cần sửa");
                    return;
                }
                for (SinhVien sv : arrSinhVien) {
                    if (sv.getMaSV().equals(maSV)) {
                        sv.setTenSV(tenSV);
                        adapter.notifyDataSetChanged();
                        edtMaSV.setText("");
                        edtTenSV.setText("");
                        tvThongBao.setText("Sửa sinh viên thành công");
                        tvSoLuong.setText("Số lượng sinh viên: " + getSoLuongSV());
                        return;
                    }
                    // Nếu không tìm thấy sinh viên cần sửa
                    tvThongBao.setText("Không tìm thấy sinh viên cần sửa");
                }
            }
        });
    }

    // Hàm kiểm tra mã sinh viên đã tồn tại chưa
    public boolean checkMaSV(String maSV) {
        for (SinhVien sv : arrSinhVien) {
            if (sv.getMaSV().equals(maSV)) {
                return true;
            }
        }
        return false;
    }

    // Hàm trả về số lượng sinh viên hiên tại
    public int getSoLuongSV() {
        return arrSinhVien.size();
    }

}