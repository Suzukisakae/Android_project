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
import com.example.tuan6_21110940_quanliphongban.modal.ChucVu;
import com.example.tuan6_21110940_quanliphongban.modal.NhanVien;
import com.example.tuan6_21110940_quanliphongban.modal.PhongBan;

import java.util.ArrayList;

public class ThietLapTruongPhongActivity extends AppCompatActivity {
    ListView lvtruongphong, lvphophong;
    ArrayList<NhanVien> arrNvForTP = new ArrayList<NhanVien>();
    ArrayAdapter<NhanVien> adapterForTP;
    ArrayList<NhanVien> arrNvForPP = new ArrayList<NhanVien>();
    ArrayAdapter<NhanVien> adapterForPP;
    ImageButton btnApply;
    int lastChecked = -1;
    PhongBan pb = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thiet_lap_truong_phong);
        getFormWidgets();
    }

    public void getFormWidgets() {
        lvtruongphong = (ListView) findViewById(R.id.lvtruongphong);
        lvtruongphong.setTextFilterEnabled(true);
        lvtruongphong.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvtruongphong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            boolean somethingChecked = false;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrNvForTP.get(position).setChucVu(ChucVu.TruongPhong);
                if (somethingChecked) {
                    CheckedTextView cv = (CheckedTextView) view;
                    cv.setChecked(false);
                }
                CheckedTextView cv = (CheckedTextView) view;
                if (!cv.isChecked()) {
                    cv.setChecked(true);
                    arrNvForTP.get(position).setChucVu(ChucVu.TruongPhong);
                } else {
                    arrNvForTP.get(position).setChucVu(ChucVu.NhanVien);
                }
                lastChecked = position;
                somethingChecked = true;
            }
        });
        lvphophong = (ListView) findViewById(R.id.lvphophong);
        lvphophong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView cv = (CheckedTextView) view;
                if (!cv.isChecked()) {
                    cv.setChecked(true);
                    arrNvForPP.get(position).setChucVu(ChucVu.PhoPhong);
                } else {
                    cv.setChecked(false);
                    arrNvForPP.get(position).setChucVu(ChucVu.NhanVien);
                }
            }
        });
        adapterForTP = new ArrayAdapter<NhanVien>(this, android.R.layout.simple_list_item_single_choice, arrNvForTP);
        adapterForPP = new ArrayAdapter<NhanVien>(this, android.R.layout.simple_list_item_multiple_choice, arrNvForPP);
        lvtruongphong.setAdapter(adapterForTP);
        lvphophong.setAdapter(adapterForPP);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("DATA");
        pb = (PhongBan) b.getSerializable("PHONGBAN");
        addNvToListTP(pb);
        addNvToListPP(pb);
        adapterForTP.notifyDataSetChanged();
        adapterForPP.notifyDataSetChanged();

        btnApply = (ImageButton) findViewById(R.id.imaapply);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doApply();
            }
        });
    }
    public void doApply() {
        Intent i = getIntent();
        Bundle b = new Bundle();
        b.putSerializable("PHONGBAN", pb);
        i.putExtra("DATA", b);
        setResult(MainActivity.THIET_LAP_TP_PP_THANHCONG, i);
        finish();
    }

    public void addNvToListTP(PhongBan pb) {
        arrNvForTP.clear();
        for (NhanVien nv : pb.getListNhanVien()) {
            arrNvForTP.add(nv);
        }
    }
    public void addNvToListPP(PhongBan pb) {
        arrNvForPP.clear();
        for (NhanVien nv : pb.getListNhanVien()) {
            arrNvForPP.add(nv);
        }
    }
}