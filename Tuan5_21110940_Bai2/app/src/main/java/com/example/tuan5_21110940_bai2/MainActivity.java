/*
 * Nguoi viet: Le Thanh Vinh
 * MSSV: 21110940
 * Bai 2: Tao Spinner
 * */
package com.example.tuan5_21110940_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Tao mang chua du lieu
    String[] arr = {"Hang dien tu", "Hang hoa chat", "Hang tieu dung", "Hang thuc pham", "Hang gia dung"};
    TextView selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selection = (TextView) findViewById(R.id.selection);
        // Lay doi tuong tu Spinner ra
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        // Gan du lieu vao Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr);
        // pahi goi lenh nay de hien thi danh sach cho Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);
        // THiet lap su kien chon phan tu trong Spinner
        spinner.setOnItemSelectedListener(new MyProcessEvent());
    }

    private class MyProcessEvent implements AdapterView.OnItemSelectedListener {
        // Khi co chon phan tu nao do thi se thuc hien phuong thuc nay
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            selection.setText(arr[pos]);
        }

        // Neu nhu khong chon gi ca
        public void onNothingSelected(AdapterView<?> parent) {
            selection.setText("");
        }
    }

    // Them menu vao Activity
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Tao doi tuong MenuInflater
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}