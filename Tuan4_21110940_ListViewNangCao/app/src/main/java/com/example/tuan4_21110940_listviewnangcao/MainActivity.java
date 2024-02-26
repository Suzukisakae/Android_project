/*
* Người viết: Lê THành Vinh
* Mã số sinh viên: 21110940
* Bài tập tuần 4 - Ứng dụng ListView nâng cao
* */

package com.example.tuan4_21110940_listviewnangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Anime> animeArrayList;
    AnimeAdapter animeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        animeArrayList = new ArrayList<>();

        animeArrayList.add(new Anime("Lê Thành Vinh", "21110940", R.drawable.v));
        animeArrayList.add(new Anime("One Piece", "Like: 2000", R.drawable.a2));
        animeArrayList.add(new Anime("Dragon Ball", "Like: 3000", R.drawable.a3));
        animeArrayList.add(new Anime("Attack on Titan", "Like: 4000", R.drawable.a4));
        animeArrayList.add(new Anime("My Hero Academia", "Like: 5000", R.drawable.a5));
        animeArrayList.add(new Anime("Demon Slayer", "Like: 6000", R.drawable.a6));
        animeArrayList.add(new Anime("Tokyo Revengers", "Like: 7000", R.drawable.a7));
        animeArrayList.add(new Anime("Black Clover", "Like: 8000", R.drawable.a8));
        animeArrayList.add(new Anime("Jujutsu Kaisen", "Like: 9000", R.drawable.a9));
        animeArrayList.add(new Anime("Boruto", "Like: 10000", R.drawable.a10));

        animeAdapter = new AnimeAdapter(animeArrayList, this);
        listView.setAdapter(animeAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu,menu);
        return true;
    }
}