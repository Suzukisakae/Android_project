package com.example.tuan6_21110940_quanliphongban.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.tuan6_21110940_quanliphongban.R;
import com.example.tuan6_21110940_quanliphongban.modal.NhanVien;

import java.util.ArrayList;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {
    Activity context;
    int layoutId;
    ArrayList<NhanVien> arrNhanVien;
    public NhanVienAdapter(Activity context, int textViewResourceId, ArrayList<NhanVien> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.layoutId = textViewResourceId;
        this.arrNhanVien = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        convertView = context.getLayoutInflater().inflate(layoutId, null);
        TextView txtnv = (TextView) convertView.findViewById(R.id.txtShortInfo);
        TextView txtmotanv = (TextView) convertView.findViewById(R.id.txtDetailInfo);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgview);

        // Lay doi tuong nhan vien tai vi tri position
        NhanVien nv = arrNhanVien.get(position);
        txtnv.setText(nv.toString());
        String strMota="";
        String cv = "Chức vụ: "+nv.getChucVu().getChucVu();
        String gt = "Giới tính: "+(nv.isGioiTinh()?"Nữ":"Nam");

        img.setImageResource(R.drawable.girlicon);
        if (!nv.isGioiTinh())
            img.setImageResource(R.drawable.boyicon);
        strMota = cv + "\n" + gt;
        txtmotanv.setText(strMota);
        return convertView;
    }
}
