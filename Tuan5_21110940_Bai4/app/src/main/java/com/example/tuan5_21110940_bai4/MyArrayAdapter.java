/*
 * Nguoi viet: Le Thanh Vinh
 * MSSV: 21110940
 * Bai 5: Xay dung ung dung quan ly danh sach nhan vien
 * */
package com.example.tuan5_21110940_bai4;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Employee> {
    Activity context = null;
    ArrayList<Employee> myArray = null;
    int layoutId;
    public MyArrayAdapter(@NonNull Activity context, int layoutId, ArrayList<Employee> arr){
        super(context, layoutId, arr);
        this.context = context;
        this.layoutId = layoutId;
        this.myArray = arr;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);
        if(myArray.size() > 0 && position >= 0){
            final TextView txtDisplay = (TextView) convertView.findViewById(R.id.txtitem);
            // lay ra nhan vien thu position
            final Employee emp = myArray.get(position);
            // hien thi len TextView
            txtDisplay.setText(emp.toString());
            // lay ra ImageView de dat hinh anh tuong ung
            final ImageView imgItem = (ImageView) convertView.findViewById(R.id.imgitem);
            // Neu la nu thi lay hinh tuong ung
            if(emp.isGender()){
                imgItem.setImageResource(R.drawable.girlicon);
            }else{ // nguoc lai la nam
                imgItem.setImageResource(R.drawable.boyicon);
            }
        }
        return convertView;
    }
}
