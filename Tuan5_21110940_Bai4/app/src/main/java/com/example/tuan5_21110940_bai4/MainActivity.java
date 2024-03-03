/*
 * Nguoi viet: Le Thanh Vinh
 * MSSV: 21110940
 * Bai 5: Xay dung ung dung quan ly danh sach nhan vien
 * */
package com.example.tuan5_21110940_bai4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Employee> arrEmployee = new ArrayList<Employee>();
    MyArrayAdapter adapter = null;
    ListView lvNhanvien = null;

    Button btnNhap;
    ImageButton btnRemoveAll;
    EditText editMa, editTen;
    RadioGroup genderGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNhap = (Button) findViewById(R.id.btnNhap);
        btnRemoveAll = (ImageButton) findViewById(R.id.btndelete);
        editMa = (EditText) findViewById(R.id.editMa);
        editTen = (EditText) findViewById(R.id.editTen);
        genderGroup = (RadioGroup) findViewById(R.id.radioGroup1);

        lvNhanvien = (ListView) findViewById(R.id.lvnhanvien);
        arrEmployee = new ArrayList<Employee>();

        // Khởi tạo đối tượng adapter và gán Data source
        adapter = new MyArrayAdapter(this, R.layout.my_item_layout, arrEmployee);
        lvNhanvien.setAdapter(adapter);

        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                xuLyNhap();
            }
        });
        btnRemoveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                xuLyXoa();
            }
        });
    }

    public void xuLyNhap() {
        String ma = editMa.getText() + "";
        String ten = editTen.getText() + "";
        boolean gioitinh = false;
        if (genderGroup.getCheckedRadioButtonId() == R.id.radNu) {
            gioitinh = true;
        }
        Employee emp = new Employee();
        emp.setId(ma);
        emp.setName(ten);
        emp.setGender(gioitinh);
        arrEmployee.add(emp);
        adapter.notifyDataSetChanged();
        editMa.setText("");
        editTen.setText("");
        editMa.requestFocus();
    }

    public void xuLyXoa() {
        for (int i = lvNhanvien.getChildCount() - 1; i >= 0; i--) {
            View v = lvNhanvien.getChildAt(i);
            CheckBox chk = (CheckBox) v.findViewById(R.id.chkitem);
            if (chk.isChecked()) {
                arrEmployee.remove(i);
            }
        }
        adapter.notifyDataSetChanged();
    }

    // Them menu vao activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}