package com.example.tuan13_21110940_lethanhvinh_firebase;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ListviewAdapter extends ArrayAdapter {
    private ArrayList<ListviewModal> ListViewArrayList;
    Context context;
    public ListviewAdapter(Context context, int layoutToBeInflated, ArrayList<ListviewModal> ListViewArrayList){
        super(context, layoutToBeInflated, ListViewArrayList);
        this.context = context;
        this.ListViewArrayList = ListViewArrayList;
    }
    private static class ViewHolder{
        private ImageView img;
        private TextView id;
        private TextView name;
        private TextView diem;

    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ListviewModal listviewModal = ListViewArrayList.get(position);
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.listview_custom, null);
            viewHolder.img = convertView.findViewById(R.id.imageView);
            viewHolder.id = convertView.findViewById(R.id.tvID);
            viewHolder.name = convertView.findViewById(R.id.tvTenSV);
            viewHolder.diem = convertView.findViewById(R.id.tvDiemSV);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.img.setImageResource(listviewModal.getImage());
        viewHolder.id.setText(String.valueOf(listviewModal.getId()));
        viewHolder.name.setText("Họ và tên: " + listviewModal.getName());
        viewHolder.diem.setText(String.valueOf(listviewModal.getDiem()));
        return convertView;
    }
}
