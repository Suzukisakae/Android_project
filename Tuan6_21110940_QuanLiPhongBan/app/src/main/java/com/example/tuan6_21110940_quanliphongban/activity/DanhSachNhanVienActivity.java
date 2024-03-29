package com.example.tuan6_21110940_quanliphongban.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;

import com.example.tuan6_21110940_quanliphongban.R;
import com.example.tuan6_21110940_quanliphongban.adapter.NhanVienAdapter;
import com.example.tuan6_21110940_quanliphongban.modal.NhanVien;
import com.example.tuan6_21110940_quanliphongban.modal.PhongBan;

import java.util.ArrayList;

public class DanhSachNhanVienActivity extends AppCompatActivity {
    TextView txtmsg;
    ImageButton btnback;
    ListView lvNhanVien;
    ArrayList<NhanVien> arrNhanVien = null;

    //
    NhanVienAdapter adapter = null;
    PhongBan pb = null;
    private NhanVien nvSelected = null;
    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_nhan_vien);

        txtmsg = (TextView) findViewById(R.id.txtmsg);
        btnback = (ImageButton) findViewById(R.id.btnback);
        lvNhanVien = (ListView) findViewById(R.id.lvnhanvien);
        getDataFromMain();
        addEvents();
        registerForContextMenu(lvNhanVien);
    }

    public void getDataFromMain() {
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("DATA");
        pb = (PhongBan) b.getSerializable("PHONGBAN");
        arrNhanVien = pb.getListNhanVien();
        adapter = new NhanVienAdapter(this, R.layout.layout_item_custom, arrNhanVien);
        lvNhanVien.setAdapter(adapter);
        txtmsg.setText("Danh sách nhân viên của phòng ban [" + pb.getTen() + "]");
    }

    public void addEvents() {
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUpdateToMain();
            }
        });
        lvNhanVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                nvSelected = arrNhanVien.get(arg2);
                position = arg2;
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextmenu_nhanvien, menu);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mnusuanv) {
            doSuaNhanVien();
        } else if (id == R.id.mnuchuyenpb) {
            doChuyenPhongBan();
        } else if (id == R.id.mnuxoanv) {
            doXoaNhanVien();
        }
//        switch (item.getItemId()) {
//            case R.id.mnusuanv:
//                doSuaNhanVien();
//                break;
//            case R.id.mnuchuyenpb:
//                doChuyenPhongBan();
//                break;
//            case R.id.mnuxoanv:
//                doXoaNhanVien();
//                break;
//        }
        return super.onContextItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == MainActivity.SUA_NHAN_VIEN_THANHCONG) {
            Bundle b= data.getBundleExtra("DATA");
            NhanVien nv = (NhanVien) b.getSerializable("NHANVIEN");
            arrNhanVien.set(position, nv);
            adapter.notifyDataSetChanged();
        }
        else if (resultCode == MainActivity.CHUYEN_PHONG_THANHCONG) {
            arrNhanVien.remove(nvSelected);
            adapter.notifyDataSetChanged();
        }
    }
    public void doSuaNhanVien() {
        Intent i = new Intent(this, SuaNhanVienActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("NHANVIEN", nvSelected);
        i.putExtra("DATA", b);
        startActivityForResult(i, MainActivity.MO_ACTIVITY_SUA_NHAN_VIEN);
    }
    public void doChuyenPhongBan() {
        Intent i = new Intent(this, ChuyenPhongBanActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("NHANVIEN", nvSelected);
        i.putExtra("DATA", b);
        startActivityForResult(i, MainActivity.MO_ACTIVITY_CHUYENPHONG);
    }
    public void doXoaNhanVien() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa ["+nvSelected.getTen()+"] không?");
        builder.setIcon(android.R.drawable.ic_input_delete);
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
            }
        });
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrNhanVien.remove(nvSelected);
                adapter.notifyDataSetChanged();
            }
        });
        builder.show();
    }
    public void doUpdateToMain() {
        Intent i = new Intent();
        Bundle b = new Bundle();
        b.putSerializable("PHONGBAN", pb);
        i.putExtra("DATA", b);
        setResult(MainActivity.CAPNHAT_DS_NHAN_VIEN_THANHCONG, i);
        finish();
    }
}