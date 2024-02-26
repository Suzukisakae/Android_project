/*
 * Hoj và tên: Lê Thành Vinh
 * Mã sinh viên: 21110940
 * Bài tập: Tạo listview cơ bản
 **/

package com.example.a21110940_tuan4_listviewcoban;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edtTenSP = (EditText) findViewById(R.id.et_tenSP);
        EditText edtMauSac = (EditText) findViewById(R.id.et_mauSac);
        Button btnMua = (Button) findViewById(R.id.btnMua);
        // Tham chieu den listview tren giao dien qua id
        ListView lvSanPham = (ListView) findViewById(R.id.lvSanPham);
        // Tao doi tuong ArrayAdapter va gan du lieu vao doi tuong san pham
        ArrayAdapter<SanPham> adapterSanPham;
        adapterSanPham = new ArrayAdapter<SanPham>(this, android.R.layout.simple_list_item_1);
        adapterSanPham.add(new SanPham("Iphone 12", "Đen"));
        adapterSanPham.add(new SanPham("Iphone 11", "Trắng"));
        adapterSanPham.add(new SanPham("Iphone 10", "Xanh"));

        // Gan adapter cho listview
        lvSanPham.setAdapter(adapterSanPham);

        // Day len cac edittext
        lvSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SanPham sp = adapterSanPham.getItem(position);
                edtTenSP.setText(sp.getTenSp());
                edtMauSac.setText(sp.getMauSac());
            }
        });
        lvSanPham.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                SanPham sp = adapterSanPham.getItem(position);
                adapterSanPham.remove(sp);
                adapterSanPham.notifyDataSetChanged();
                return false;
            }
        });

        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenSP = edtTenSP.getText().toString();
                String mauSac = edtMauSac.getText().toString();
                if (tenSP.isEmpty() || mauSac.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    SanPham sp = new SanPham(tenSP, mauSac);
                    adapterSanPham.add(sp);
                    adapterSanPham.notifyDataSetChanged();
                    edtTenSP.setText("");
                    edtMauSac.setText("");
                    Toast.makeText(MainActivity.this, "Mua thành công \n" + sp.getTenSp() + "\n" + sp.getMauSac(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu,menu);
        return true;
    }
}