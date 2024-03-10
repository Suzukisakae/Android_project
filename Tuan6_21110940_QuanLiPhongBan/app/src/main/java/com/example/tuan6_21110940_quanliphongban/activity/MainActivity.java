/*
* Nguoi viet: Le Thanh Vinh
* MSSV: 21110940
* Noi dung: Bai tap tuan 6 - Quan li phong ban - nhan vien
* */
package com.example.tuan6_21110940_quanliphongban.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.tuan6_21110940_quanliphongban.R;
import com.example.tuan6_21110940_quanliphongban.adapter.PhongBanAdapter;
import com.example.tuan6_21110940_quanliphongban.modal.ChucVu;
import com.example.tuan6_21110940_quanliphongban.modal.NhanVien;
import com.example.tuan6_21110940_quanliphongban.modal.PhongBan;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Khai bao cac Request + Result code de xu li Intent for result
    public static final int MO_ACTIVITY_THEM_NHAN_VIEN = 1;
    public static final int MO_ACTIVITY_SUA_NHAN_VIEN = 2;
    public static final int THEM_NHAN_VIEN_THANHCONG = 3;
    public static final int SUA_NHAN_VIEN_THANHCONG = 4;
    public static final int XEM_DS_NHAN_VIEN = 5;
    public static final int CAPNHAT_DS_NHAN_VIEN_THANHCONG = 6;
    public static final int MO_ACTIVITY_THIET_LAP_TP_PP = 7;
    public static final int THIET_LAP_TP_PP_THANHCONG = 8;
    public static final int MO_ACTIVITY_CHUYENPHONG = 9;
    public static final int CHUYEN_PHONG_THANHCONG = 10;

    private Button btnLuuPb;
    private EditText editMapb, editTenpb;
    private ListView lvpb;
    private static ArrayList<PhongBan> arrPhongBan = new ArrayList<PhongBan>();

    private PhongBanAdapter adapterPb = null;
    private PhongBan pbSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFormWidgets();
        addEvents();
        fakeData();
    }

    public void fakeData() {
        NhanVien nv = null;
        PhongBan pb = new PhongBan("PB01", "Phòng ky thuat");
        nv = new NhanVien("NV01", "Nguyen Van A", true);
        nv.setChucVu(ChucVu.TruongPhong);
        pb.themNV(nv);

        nv = new NhanVien("NV02", "Nguyen Van B", false);
        nv.setChucVu(ChucVu.PhoPhong);
        pb.themNV(nv);

        nv = new NhanVien("NV03", "Nguyen Van C", true);
        nv.setChucVu(ChucVu.TruongPhong);
        pb.themNV(nv);

        arrPhongBan.add(pb);
        pb = new PhongBan("PB02", "Phòng dich vu");
        arrPhongBan.add(pb);
        pb = new PhongBan("PB03", "Phòng truyen thong");
        arrPhongBan.add(pb);

        nv = new NhanVien("m1", "Nguyen Van D", false);
        nv.setChucVu(ChucVu.NhanVien);
        pb.themNV(nv);

        nv = new NhanVien("m2", "Nguyen Van E", true);
        nv.setChucVu(ChucVu.TruongPhong);
        pb.themNV(nv);

        nv = new NhanVien("m3", "Nguyen Van F", false);
        nv.setChucVu(ChucVu.PhoPhong);
        pb.themNV(nv);

        adapterPb.notifyDataSetChanged();
    }

    public void getFormWidgets() {
        btnLuuPb = (Button) findViewById(R.id.btnluupb);
        editMapb = (EditText) findViewById(R.id.editmapb);
        editTenpb = (EditText) findViewById(R.id.edittenpb);
        lvpb = (ListView) findViewById(R.id.lvphongban);
        arrPhongBan = new ArrayList<PhongBan>();
        adapterPb = new PhongBanAdapter(this, R.layout.layout_item_custom, arrPhongBan);
        lvpb.setAdapter(adapterPb);
        registerForContextMenu(lvpb);
    }

    public void addEvents() {
        btnLuuPb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLuuPhongBan();
            }
        });
        lvpb.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pbSelected = arrPhongBan.get(position);
                return false;
            }
        });
    }

    public void doLuuPhongBan() {
        String mapb = editMapb.getText() + "";
        String tenpb = editTenpb.getText() + "";
        PhongBan pb = new PhongBan(mapb, tenpb);
        arrPhongBan.add(pb);
        adapterPb.notifyDataSetChanged();
        editMapb.setText("");
        editTenpb.setText("");
        editMapb.requestFocus();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contextmenu_phongban, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mnuthemnv) {
            doThemNhanVien();
        } else if (id == R.id.mnudanhsachnv) {
            doDanhSachNhanVien();
        } else if (id == R.id.mnutruongphong) {
            doThietLapLanhDao();
        } else if (id == R.id.mnuxoapb) {
            doXoaPhongBan();
        }
        return super.onContextItemSelected(item);
    }

    public void doThemNhanVien() {
        Intent i = new Intent(this, ThemNhanVienActivity.class);
        startActivityForResult(i, MO_ACTIVITY_THEM_NHAN_VIEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == THEM_NHAN_VIEN_THANHCONG) {
            Bundle b = data.getBundleExtra("DATA");
            NhanVien nv = (NhanVien) b.getSerializable("NHANVIEN");
            pbSelected.themNV(nv);
            adapterPb.notifyDataSetChanged();
        }
        else if (resultCode == THIET_LAP_TP_PP_THANHCONG || resultCode == CAPNHAT_DS_NHAN_VIEN_THANHCONG) {
            Bundle b = data.getBundleExtra("DATA");
            PhongBan pb = (PhongBan) b.getSerializable("PHONGBAN");
            pbSelected.getListNhanVien().clear();
            pbSelected.getListNhanVien().addAll(pb.getListNhanVien());
            adapterPb.notifyDataSetChanged();
        }
    }
    public void doDanhSachNhanVien() {
        Intent i = new Intent(this, DanhSachNhanVienActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("PHONGBAN", pbSelected);
        i.putExtra("DATA", b);
        startActivityForResult(i, XEM_DS_NHAN_VIEN);
    }
    public void doThietLapLanhDao() {
        Intent i = new Intent(this, ThietLapTruongPhongActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("PHONGBAN", pbSelected);
        i.putExtra("DATA", b);
        startActivityForResult(i, MO_ACTIVITY_THIET_LAP_TP_PP);
    }
    public void doXoaPhongBan() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận xóa phòng ban");
        builder.setMessage("Bạn có chắc chắn muốn xóa phòng ban ["+pbSelected.getTen()+"] không?");
        builder.setIcon(android.R.drawable.ic_input_delete);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrPhongBan.remove(pbSelected);
                adapterPb.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
    public static ArrayList<PhongBan> getListPhongBan() {
        return arrPhongBan;
    }
}