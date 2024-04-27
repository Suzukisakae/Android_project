package com.example.tuan12_21110940_lethanhvinh_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText ngayCapNhat, heSo, diem;
    TextView tvID;
    Spinner DSMonhoc, DSSinhvien;
    ListView lsDanhSach;
    Button btnThem, btnSua, btnXoa, btnClear;
    ArrayList<ListviewModal> arrListView = new ArrayList<>();
    ArrayAdapter<ListviewModal> adapterListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvID = findViewById(R.id.tvID);

        ngayCapNhat = findViewById(R.id.edtNgayCapNhat);
        heSo = findViewById(R.id.edtHeSo);
        diem = findViewById(R.id.edtDiem);

        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        btnClear = findViewById(R.id.btnClear);

        DSMonhoc = findViewById(R.id.spMonHoc);
        DSSinhvien = findViewById(R.id.spSinhVien);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        sqLiteHelper.deleteAllSinhVien();
        sqLiteHelper.addSinhVien(new SinhVien(1, "Lê THành Vinh", 10.0f, "C#"));
        sqLiteHelper.addSinhVien(new SinhVien(2, "NGuyen Van A", 90.0f, "Android"));
        sqLiteHelper.addSinhVien(new SinhVien(3, "NGuyen Van B", 70.0f, "PHP"));
        sqLiteHelper.addSinhVien(new SinhVien(4, "Le Van C", 80.0f, "NodeJS"));

//        Them cac mon hoc vao spinner
        ArrayList<String> arrMonHoc = new ArrayList<>();
        arrMonHoc.add("C#");
        arrMonHoc.add("Android");
        arrMonHoc.add("PHP");
        arrMonHoc.add("NodeJS");
        ArrayAdapter<String> adapterMonHoc = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrMonHoc);
        adapterMonHoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DSMonhoc.setAdapter(adapterMonHoc);

//        Them cac sinh vien vao spinner
        ArrayList<String> arrSinhVien = new ArrayList<>();
        arrSinhVien.add("Lê THành Vinh");
        arrSinhVien.add("NGuyen Van A");
        arrSinhVien.add("NGuyen Van B");
        arrSinhVien.add("Le Van C");
        ArrayAdapter<String> adapterSinhVien = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrSinhVien);
        adapterSinhVien.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DSSinhvien.setAdapter(adapterSinhVien);


        lsDanhSach = findViewById(R.id.lvDanhSach);
        adapterListView = new ListviewAdapter(this, R.layout.listview_custom, arrListView);
        List<SinhVien> list = sqLiteHelper.getAllSinhVien();
        for (SinhVien sv : list) {
            if (sv.getMonhoc().equals("C#")) {
                arrListView.add(new ListviewModal(R.drawable.images, sv.getId(), sv.getName(), sv.getDiem()));
            } else if (sv.getMonhoc().equals("Android")) {
                arrListView.add(new ListviewModal(R.drawable.android, sv.getId(), sv.getName(), sv.getDiem()));
            } else if (sv.getMonhoc().equals("PHP")) {
                arrListView.add(new ListviewModal(R.drawable.php, sv.getId(), sv.getName(), sv.getDiem()));
            } else if (sv.getMonhoc().equals("NodeJS")) {
                arrListView.add(new ListviewModal(R.drawable.node, sv.getId(), sv.getName(), sv.getDiem()));
            }
        }
        lsDanhSach.setAdapter(adapterListView);

        lsDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListviewModal listviewModal = arrListView.get(position);
                DSSinhvien.setSelection(adapterSinhVien.getPosition(listviewModal.getName()));
                if (listviewModal.getImage() == R.drawable.images) {
                    DSMonhoc.setSelection(adapterMonHoc.getPosition("C#"));
                } else if (listviewModal.getImage() == R.drawable.android) {
                    DSMonhoc.setSelection(adapterMonHoc.getPosition("Android"));
                } else if (listviewModal.getImage() == R.drawable.php) {
                    DSMonhoc.setSelection(adapterMonHoc.getPosition("PHP"));
                } else if (listviewModal.getImage() == R.drawable.node) {
                    DSMonhoc.setSelection(adapterMonHoc.getPosition("NodeJS"));
                }
                diem.setText(String.valueOf(listviewModal.getDiem()));
                ngayCapNhat.setText("2024-5-1");
                heSo.setText("1.0");
                tvID.setText(String.valueOf(listviewModal.getId()));
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String tenSV = DSSinhvien.getSelectedItem().toString();
                    String monHoc = DSMonhoc.getSelectedItem().toString();
                    float diemSV = Float.parseFloat(diem.getText().toString());
                    int id = sqLiteHelper.getMaxID() + 1;
                    SinhVien sinhVien = new SinhVien(id, tenSV, diemSV, monHoc);
                    sqLiteHelper.addSinhVien(sinhVien);
                    if (diem.getText() == null) {
                        Toast.makeText(MainActivity.this, "Vui lòng nhập điểm", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (monHoc.equals("C#")) {
                        arrListView.add(new ListviewModal(R.drawable.images, id, tenSV, diemSV));
                    } else if (monHoc.equals("Android")) {
                        arrListView.add(new ListviewModal(R.drawable.android, id, tenSV, diemSV));
                    } else if (monHoc.equals("PHP")) {
                        arrListView.add(new ListviewModal(R.drawable.php, id, tenSV, diemSV));
                    } else if (monHoc.equals("NodeJS")) {
                        arrListView.add(new ListviewModal(R.drawable.node, id, tenSV, diemSV));
                    }
                    adapterListView.notifyDataSetChanged();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String tenSV = DSSinhvien.getSelectedItem().toString();
                    String monHoc = DSMonhoc.getSelectedItem().toString();
                    float diemSV = Float.parseFloat(diem.getText().toString());
                    int id = tvID.getText().toString().equals("") ? 0 : Integer.parseInt(tvID.getText().toString());
                    SinhVien sinhVien = new SinhVien(id, tenSV, diemSV, monHoc);
                    sqLiteHelper.updateSinhVien(sinhVien);
                    if (diem.getText() == null) {
                        Toast.makeText(MainActivity.this, "Vui lòng nhập điểm", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (monHoc.equals("C#")) {
                        arrListView.add(new ListviewModal(R.drawable.images, id, tenSV, diemSV));
                    } else if (monHoc.equals("Android")) {
                        arrListView.add(new ListviewModal(R.drawable.android, id, tenSV, diemSV));
                    } else if (monHoc.equals("PHP")) {
                        arrListView.add(new ListviewModal(R.drawable.php, id, tenSV, diemSV));
                    } else if (monHoc.equals("NodeJS")) {
                        arrListView.add(new ListviewModal(R.drawable.node, id, tenSV, diemSV));
                    }
                    adapterListView.notifyDataSetChanged();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int id = tvID.getText().toString().equals("") ? 0 : Integer.parseInt(tvID.getText().toString());
                    SinhVien sinhVien = new SinhVien(id, "", 0, "");
                    sqLiteHelper.deleteSinhVien(sinhVien);
                    for (int i = 0; i < arrListView.size(); i++) {
                        if (arrListView.get(i).getId() == id) {
                            arrListView.remove(i);
                            break;
                        }
                    }
                    adapterListView.notifyDataSetChanged();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DSSinhvien.setSelection(0);
                DSMonhoc.setSelection(0);
                diem.setText("");
                ngayCapNhat.setText("");
                heSo.setText("");
                tvID.setText("");
            }
        });

    }
//    them menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnThem) {
            Toast.makeText(this, "Bạn vừa chọn thêm", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.mnHien) {
//            Load lại data
            arrListView.clear();
            SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
            List<SinhVien> list = sqLiteHelper.getAllSinhVien();
            for (SinhVien sv : list) {
                if (sv.getMonhoc().equals("C#")) {
                    arrListView.add(new ListviewModal(R.drawable.images, sv.getId(), sv.getName(), sv.getDiem()));
                } else if (sv.getMonhoc().equals("Android")) {
                    arrListView.add(new ListviewModal(R.drawable.android, sv.getId(), sv.getName(), sv.getDiem()));
                } else if (sv.getMonhoc().equals("PHP")) {
                    arrListView.add(new ListviewModal(R.drawable.php, sv.getId(), sv.getName(), sv.getDiem()));
                } else if (sv.getMonhoc().equals("NodeJS")) {
                    arrListView.add(new ListviewModal(R.drawable.node, sv.getId(), sv.getName(), sv.getDiem()));
                }
            }
            adapterListView.notifyDataSetChanged();
        }
        else if (item.getItemId() == R.id.mnThoat) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}