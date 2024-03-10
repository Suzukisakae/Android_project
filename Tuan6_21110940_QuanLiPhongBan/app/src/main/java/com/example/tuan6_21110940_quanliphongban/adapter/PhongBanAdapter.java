package com.example.tuan6_21110940_quanliphongban.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.tuan6_21110940_quanliphongban.modal.NhanVien;
import com.example.tuan6_21110940_quanliphongban.modal.PhongBan;
import com.example.tuan6_21110940_quanliphongban.R;

import java.util.ArrayList;

public class PhongBanAdapter extends ArrayAdapter<PhongBan> {
    Activity context;
    int layoutId;
    ArrayList<PhongBan> arrPhongBan;
    public PhongBanAdapter(Activity context, int textViewResourceId, ArrayList<PhongBan> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.layoutId = textViewResourceId;
        this.arrPhongBan = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        convertView = context.getLayoutInflater().inflate(layoutId, null);
        TextView txtpb = (TextView) convertView.findViewById(R.id.txtShortInfo);
        TextView txtmotapb = (TextView) convertView.findViewById(R.id.txtDetailInfo);

        // Lay doi tuong phong ban tai vi tri position
        PhongBan pb = arrPhongBan.get(position);
        txtpb.setText(pb.toString());
        String strMota="";
        String tp ="Trưởng phòng: [Chua co] ";
        NhanVien nv = pb.getTruongPhong();
        if (nv!=null)
            tp = "Trưởng phòng: ["+nv.getTen()+"] ";
        ArrayList<NhanVien> dsPhoPhong = pb.getPhoPhong();
        String pp = "Phó phòng: [Chua co] ";
        if (dsPhoPhong.size()>0) {
            pp = "Phó phòng: \n";
            for (int i=0; i<dsPhoPhong.size(); i++) {
                pp+= (i+1)+". "+dsPhoPhong.get(i).getTen()+"\n";
            }
        }
        strMota = tp + "\n" + pp;
        txtmotapb.setText(strMota);
        return convertView;
    }
}
