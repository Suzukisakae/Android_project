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
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    View footerView;
    ArrayList<Anime> animeArrayList;
    AnimeAdapter animeAdapter;
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        footerView = getLayoutInflater().inflate(R.layout.footer_view, null);
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

        listView.addFooterView(footerView);
        // Add footer view khi listvew setOnScrollListener với firstVisibleItem + visibleItemCount == totalItemCount
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && !isLoading) {
                    isLoading = true;
                    // Sử dụng Handler để hiển thị footerView trong 2 giây
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Load dữ liệu ở đây
                            animeArrayList.add(new Anime("Yosuga no Sora", "Like: 11000", R.drawable.b1));
                            animeArrayList.add(new Anime("High School DxD", "Like: 12000", R.drawable.b2));
                            animeArrayList.add(new Anime("Sword Art Online", "Like: 13000", R.drawable.b3));
                            animeArrayList.add(new Anime("No Game No Life", "Like: 14000", R.drawable.b4));
                            animeArrayList.add(new Anime("Date A Live", "Like: 15000", R.drawable.b5));
                            animeArrayList.add(new Anime("Guilty Crown", "Like: 16000", R.drawable.b6));
                            animeArrayList.add(new Anime("Mirai Nikki","Like: 17000", R.drawable.b7));
                            animeArrayList.add(new Anime("Shingeki no Kyojin", "Like: 18000", R.drawable.b8));

                            animeAdapter.notifyDataSetChanged();
                            // Sau khi load xong, gọi phương thức removeFooterView để xóa footerView đi
                            listView.removeFooterView(footerView);
                        }
                    }, 2000);
                }

            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu,menu);
        return true;
    }
}