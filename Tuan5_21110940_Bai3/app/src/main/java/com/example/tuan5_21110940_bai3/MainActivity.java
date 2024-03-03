/* Nguoi viet: Le Thanh Vinh
 * MSSV: 21110940
 * Bai 3: Bai tap xoa san pham
 * */
package com.example.tuan5_21110940_bai3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> listProduct;
    ProductListViewAdapter productListViewAdapter;
    ListView listViewProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khoi tao ListProduct
        listProduct = new ArrayList<>();
        listProduct.add(new Product("Iphone 12", 20000000, 1));
        listProduct.add(new Product("Iphone 11", 15000000, 2));
        listProduct.add(new Product("Iphone 10", 10000000, 3));
        listProduct.add(new Product("Iphone 9", 8000000, 4));
        listProduct.add(new Product("Iphone 8", 6000000, 5));
        listProduct.add(new Product("Iphone 7", 4000000, 6));

        // Khoi tao ProductListViewAdapter
        listViewProduct = (ListView) findViewById(R.id.listproduct);
        productListViewAdapter = new ProductListViewAdapter(listProduct);
        listViewProduct.setAdapter(productListViewAdapter);

        // Lang nghe su kien click tren item cua ListView
        listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = (Product) productListViewAdapter.getItem(position);
                Toast.makeText(MainActivity.this, "Ban da chon san pham: " + product.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listProduct.size() > 0) {
                    // Xoa phan tu dau tien cua listProduct
                    int productpostion = 0;
                    listProduct.remove(productpostion);
                    // THong bao da xoa thanh cong
                    Toast.makeText(MainActivity.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                    // Cap nhat lai ListView
                    productListViewAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    // Them menu vao activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}