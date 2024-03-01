/*
 * Người viết: Lê Thành Vinh
 * MSSV: 21110940
 * Nội dung: Bài 1 - Cập nhật sinh viên
 * */
package com.example.tuan5_21110940_bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ListView lvSinhVien;
    // Bộ đôi để customListView
    MyArrayAdapter adapterSinhVien;
    ArrayList<Student> arrSinhVien = new ArrayList<Student>();
    EditText editMa, editTen, editNamSinh;
    CheckBox chkGender;
    Button btnNamSinh, btnNhapsv;

    AutoCompleteTextView autoTextNs;
    // Bộ đôi để cho autoCompleteTextView
    ArrayList<String> arrAuto = new ArrayList<String>();
    ArrayAdapter<String> adapterAuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFormWidgets();
        addEventsForWidgets();
    }

    public void getFormWidgets() {
        editMa = (EditText) findViewById(R.id.editMa);
        editTen = (EditText) findViewById(R.id.editTen);
        editNamSinh = (EditText) findViewById(R.id.editNgaysinh);
        chkGender = (CheckBox) findViewById(R.id.chkGT);
        autoTextNs = (AutoCompleteTextView) findViewById(R.id.autoComleNS);
        btnNamSinh = (Button) findViewById(R.id.btnNgaySinh);
        btnNhapsv = (Button) findViewById(R.id.btnNhap);

        lvSinhVien = (ListView) findViewById(R.id.lvsinhvien);
        // Gán Data Source (arrSinhVien) vào ArrayAdapter
        adapterSinhVien = new MyArrayAdapter(this, R.layout.sinhvien_item_layout, arrSinhVien);
        // Gán Adapter vào ListView
        lvSinhVien.setAdapter(adapterSinhVien);
    }

    public void addEventsForWidgets() {
        btnNamSinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processNgaySinh();
            }
        });
        btnNhapsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processInput();
                editMa.setText("");
                editMa.requestFocus();
                editTen.setText("");
                editNamSinh.setText("");
            }
        });
    }

    public void processNgaySinh() {
        DatePickerDialog.OnDateSetListener callBack = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                editNamSinh.setText(arg3 + "/" + (arg2 + 1) + "/" + arg1);
            }


        };
        DatePickerDialog dateDialog = new DatePickerDialog(this, callBack, 2021, 0, 1);
        dateDialog.setTitle("Chọn ngày sinh");
        dateDialog.show();
    }

    public void processInput() {
        String ma = editMa.getText() + "";
        String ten = editTen.getText() + "";
        String ns = editNamSinh.getText() + "";
        String nois = autoTextNs.getText() + "";
        Boolean gt = chkGender.isChecked();
        SimpleDateFormat dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Student s = new Student(ma, ten, gt, dft.parse(ns), nois);
            arrSinhVien.add(s);
            adapterSinhVien.notifyDataSetChanged();
            processAutoComplete(nois);
        } catch (ParseException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    public void processAutoComplete(String data) {
        for (int i = 0; i < arrAuto.size(); i++) {
            String x = arrAuto.get(i);
            if (x.trim().equalsIgnoreCase(data.trim())) {
                return;
            }
        }
        arrAuto.add(data);
        adapterAuto = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrAuto);
        autoTextNs.setAdapter(adapterAuto);
    }
}