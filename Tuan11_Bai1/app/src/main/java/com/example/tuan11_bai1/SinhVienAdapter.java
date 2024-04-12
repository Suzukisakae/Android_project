package com.example.tuan11_bai1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    Context context;
    int layout;
    ArrayList<SinhVien> arrayList;
    public SinhVienAdapter(@NonNull Context context, @LayoutRes int resource,@NonNull ArrayList<SinhVien> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.layout = resource;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layout, null);

        ImageView img= convertView.findViewById(R.id.image);
        TextView txt1 = convertView.findViewById(R.id.txtTen);
        TextView txt2 = convertView.findViewById(R.id.txtdiaChi);

        if (arrayList.get(position).getGioiTinh() == 1) {
            img.setImageResource(R.drawable.male);
        } else {
            img.setImageResource(R.drawable.female);
        }
        txt1.setText(arrayList.get(position).getTen());
        txt2.setText(arrayList.get(position).getDiaChi());
        return convertView;
    }
}
