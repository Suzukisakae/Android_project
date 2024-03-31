package com.example.lethanhvinh_21110940_ktgiuaki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    private ArrayList<SinhVien> SinhVienArrayList;
    Context context;

    public SinhVienAdapter(Context context, int layout, ArrayList<SinhVien> SinhVienArrayList) {
        super(context, layout, SinhVienArrayList);
        this.context = context;
        this.SinhVienArrayList = SinhVienArrayList;
    }
    private static class ViewHolder {
        TextView tvMaSV;
        TextView tvTenSV;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_listview, null);
            viewHolder.tvMaSV = convertView.findViewById(R.id.tvMaSV);
            viewHolder.tvTenSV = convertView.findViewById(R.id.tvTenSV);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SinhVien sv = SinhVienArrayList.get(position);
        viewHolder.tvMaSV.setText(sv.getMaSV());
        viewHolder.tvTenSV.setText(sv.getTenSV());
        return convertView;
    }
}
