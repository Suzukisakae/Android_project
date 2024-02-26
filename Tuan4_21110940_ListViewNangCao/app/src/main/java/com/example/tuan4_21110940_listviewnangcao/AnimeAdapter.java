/*
 * Người viết: Lê THành Vinh
 * Mã số sinh viên: 21110940
 * Bài tập tuần 4 - Ứng dụng ListView nâng cao
 * */
package com.example.tuan4_21110940_listviewnangcao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AnimeAdapter extends ArrayAdapter<Anime> {
    private ArrayList<Anime> AnimeArrayList;
    Context context;

    public AnimeAdapter(ArrayList<Anime> AnimeArrayList, Context context) {
        super(context, R.layout.item_list, AnimeArrayList);
        this.AnimeArrayList = AnimeArrayList;
        this.context = context;
    }

    private static class ViewHolder {
        TextView ten;
        TextView like;
        ImageView hinh;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,@Nullable ViewGroup parent) {
        Anime anime = getItem(position);
        ViewHolder viewHolder;
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_list, parent, false);
            viewHolder.ten = convertView.findViewById(R.id.anime_name);
            viewHolder.like = convertView.findViewById(R.id.like_count);
            viewHolder.hinh = convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ten.setText(anime.getTen());
        viewHolder.like.setText(anime.getLike());
        viewHolder.hinh.setImageResource(anime.getHinh());
        return convertView;
    }
}
