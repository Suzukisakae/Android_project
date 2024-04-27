/*
 * Người viết: Lê Thành Vinh
 * MSSV: 21110940
 * Nọi dung: Bài tập tuan 13 - DÙng Firebase
 * */
package com.example.tuan13_21110940_lethanhvinh_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//        json
        /*
        {
  "data": {
    "ID1": {
      "diem": 10,
      "monhoc": "C#",
      "name": "Lê THành Vinh"
    },
    "ID2": {
      "diem": 9,
      "monhoc": "Android",
      "name": "NGuyen Van A"
    },
    "ID3": {
      "diem": 7,
      "monhoc": "PHP",
      "name": "NGuyen Van B"
    },
    "ID4": {
      "diem": 8,
      "monhoc": "NodeJS",
      "name": "Le Van C"
    }
  }
}
        * */

public class MainActivity extends AppCompatActivity {
    EditText ngayCapNhat, heSo, diem;
    TextView tvID;
    Spinner DSMonhoc, DSSinhvien;
    ListView lsDanhSach;
    Button btnThem, btnSua, btnXoa, btnClear;
    ArrayList<ListviewModal> arrListView = new ArrayList<>();
    ArrayAdapter<ListviewModal> adapterListView = null;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference ref = db.getReference().child("data");

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
        loadListView();
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
                    if (diem.getText() == null) {
                        Toast.makeText(MainActivity.this, "Vui lòng nhập điểm", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String monhoc = DSMonhoc.getSelectedItem().toString();
                    String name = DSSinhvien.getSelectedItem().toString();
                    float diemFloat = Float.parseFloat(diem.getText().toString());
                    int id = arrListView.size() + 2;
                    // Tao node ID
                    String idString = "ID" + id;
                    ref.child(idString).child("name").setValue(name);
                    ref.child(idString).child("monhoc").setValue(monhoc);
                    ref.child(idString).child("diem").setValue(diemFloat);
                    Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    arrListView.clear();
                    loadListView();
                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("Error", e.toString());
                }

            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (diem.getText() == null) {
                        Toast.makeText(MainActivity.this, "Vui lòng nhập điểm", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String monhoc = DSMonhoc.getSelectedItem().toString();
                    String name = DSSinhvien.getSelectedItem().toString();
                    float diemFloat = Float.parseFloat(diem.getText().toString());
                    int id = Integer.parseInt(tvID.getText().toString());
                    // Tao node ID
                    String idString = "ID" + id;
                    ref.child(idString).child("name").setValue(name);
                    ref.child(idString).child("monhoc").setValue(monhoc);
                    ref.child(idString).child("diem").setValue(diemFloat);
                    Toast.makeText(MainActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("Error", e.toString());
                }
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int id = Integer.parseInt(tvID.getText().toString());
                    // Tao node ID
                    String idString = "ID" + id;
                    ref.child(idString).removeValue();
                    Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("Error", e.toString());
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
    public void loadListView() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    arrListView.clear();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        String id = data.getKey();
                        // tạo idInt từ id (bỏ ký tự "ID" ở đầu id)
                        int idInt = Integer.parseInt(id.substring(2));
                        String name = data.child("name").getValue().toString();
                        String monhoc = data.child("monhoc").getValue().toString();
                        float diem = Float.parseFloat(data.child("diem").getValue().toString());
                        if (monhoc.equals("C#")) {
                            arrListView.add(new ListviewModal(R.drawable.images, idInt, name, diem));
                        } else if (monhoc.equals("Android")) {
                            arrListView.add(new ListviewModal(R.drawable.android, idInt, name, diem));
                        } else if (monhoc.equals("PHP")) {
                            arrListView.add(new ListviewModal(R.drawable.php, idInt, name, diem));
                        } else if (monhoc.equals("NodeJS")) {
                            arrListView.add(new ListviewModal(R.drawable.node, idInt, name, diem));
                        }
                        adapterListView.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Error" + databaseError.toString(), Toast.LENGTH_SHORT).show();
                Log.d("Error", databaseError.toString());
            }
        });
    }

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
            loadListView();
        }
        else if (item.getItemId() == R.id.mnThoat) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}