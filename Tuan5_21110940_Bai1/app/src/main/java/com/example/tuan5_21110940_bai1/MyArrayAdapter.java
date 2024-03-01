/*
 * Người viết: Lê Thành Vinh
 * MSSV: 21110940
 * Nội dung: Bài 1 - Cập nhật sinh viên
 * */
package com.example.tuan5_21110940_bai1;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MyArrayAdapter extends ArrayAdapter<Student> {
    Activity context;
    int resourceId;
    ArrayList<Student> arrStudent;
    public MyArrayAdapter(Activity context, int resourceId, ArrayList<Student> objects) {
        super(context, resourceId, objects);
        this.context = context;
        this.resourceId = resourceId;
        this.arrStudent = objects;
    }

    @NonNull
    @Override
    public View getView(int position,@NonNull View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            // Gắn layout vào Activity
            convertView = context.getLayoutInflater().inflate(resourceId, null);
        }
        // Lấy TextView để luu trữ mã số và tên
        TextView txtMaTen = (TextView) convertView.findViewById(R.id.txtMavaTen);
        // Lấy TextView để luu trữ giới tính, năm sinh, nơi sinh
        TextView txtKhac = (TextView) convertView.findViewById(R.id.txtThongTinKhac);
        // lấy mã số sinh viên thứ position
        Student s = arrStudent.get(position);
        txtMaTen.setText(s.getId() + " - " + s.getName());
        // ĐỊnh dạng lại ngày tháng năm
        SimpleDateFormat dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        txtKhac.setText((s.getGender() ? "Nữ" : "Nam") + " - " + dft.format(s.getNgaysinh()) + " - " + s.getNoiSinh());
        return convertView;
    }
}
