package com.example.tuan11_bai1;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<SinhVien> arrayList;
    SinhVienAdapter sinhVienAdapter;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(MainActivity.this);
        listView = findViewById(R.id.list);
        arrayList = db.getAllSinhVien();

        sinhVienAdapter = new SinhVienAdapter(MainActivity.this, R.layout.layout_row, arrayList);
        listView.setAdapter(sinhVienAdapter);

        registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnu_them) {
            final SinhVien sinhVien = new SinhVien();
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Thêm sinh viên");
            builder.setCancelable(false);
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            View view = inflater.inflate(R.layout.layout_input, null);

            final EditText edtTen = view.findViewById(R.id.edtTen);
            final EditText edtDiaChi = view.findViewById(R.id.edtdiaChi);
            final RadioGroup rdg = view.findViewById(R.id.rdgGT);
            final ImageView img = view.findViewById(R.id.image);
            builder.setView(view);

            rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    if (checkedId == R.id.rdbNam) {
                        sinhVien.setGioiTinh(1);
                        img.setImageResource(R.drawable.male);
                    } else if (checkedId == R.id.rdbNu) {
                        sinhVien.setGioiTinh(0);
                        img.setImageResource(R.drawable.female);
                    }
                }
            });

            builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sinhVien.setTen(edtTen.getText().toString());
                    sinhVien.setDiaChi(edtDiaChi.getText().toString());
                    db.themSinhVien(sinhVien);
                    arrayList.add(sinhVien);
                    sinhVienAdapter.notifyDataSetChanged();
                }
            });

            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.mnu_xoa) {
            final SinhVien sinhVien = arrayList.get(info.position);
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Cập nhật sinh viên");
            builder.setCancelable(false);
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            View view = inflater.inflate(R.layout.layout_input, null);

            final EditText edtTen = view.findViewById(R.id.edtTen);
            final EditText edtDiaChi = view.findViewById(R.id.edtdiaChi);
            final RadioGroup rdg = view.findViewById(R.id.rdgGT);
            final ImageView img = view.findViewById(R.id.image);
            final RadioButton rdbNam = view.findViewById(R.id.rdbNam);
            final RadioButton rdbNu = view.findViewById(R.id.rdbNu);
            edtTen.setText(sinhVien.getTen());
            edtDiaChi.setText(sinhVien.getDiaChi());

            if (sinhVien.getGioiTinh() == 1) {
                rdbNam.setChecked(true);
                img.setImageResource(R.drawable.male);
            } else {
                rdbNu.setChecked(true);
                img.setImageResource(R.drawable.female);
            }
            builder.setView(view);
            rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    if (checkedId == R.id.rdbNam) {
                        sinhVien.setGioiTinh(1);
                        img.setImageResource(R.drawable.male);
                    } else if (checkedId == R.id.rdbNu) {
                        sinhVien.setGioiTinh(0);
                        img.setImageResource(R.drawable.female);
                    }
                }
            });

            builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sinhVien.setTen(edtTen.getText().toString());
                    sinhVien.setDiaChi(edtDiaChi.getText().toString());
                    db.capNhatSinhVien(sinhVien);
                    arrayList.set(info.position, sinhVien);
                    sinhVienAdapter.notifyDataSetChanged();
                }
            });

            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if (item.getItemId() == R.id.mnu_xoa) {
            final SinhVien sinhVien1 = arrayList.get(info.position);
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Xóa sinh viên");
            builder.setMessage("Bạn có chắc chắn muốn xóa sinh viên này không?");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db.xoaSinhVien(sinhVien1);
                    arrayList.remove(info.position);
                    sinhVienAdapter.notifyDataSetChanged();
                }
            });

            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onContextItemSelected(item);
    }
}