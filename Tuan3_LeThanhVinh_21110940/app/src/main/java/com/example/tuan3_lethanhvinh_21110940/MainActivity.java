/*
NGười viết: Lê Thanh Vinh
MSSV: 21110940
Lớp: Lập trình di động MOPR331279_23_2_03
Bài tập: Tuần 3
THời gian hoàn thành: 26/01/2024 15:02:00

*/

package com.example.tuan3_lethanhvinh_21110940;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Button btnDoc, btnGhi;
    EditText edtHoTen, edtSDT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thamchieu();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu,menu);
        return true;
    }
    public void thamchieu(){
        btnGhi = (Button) findViewById(R.id.btnGhi);
        btnGhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noidung = edtHoTen.getText().toString() + "#" + edtSDT.getText().toString();
                ghiFile(noidung+"\n");
            }
        });
        btnDoc = (Button) findViewById(R.id.btnDoc);
        btnDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docFile();
            }
        });
        edtHoTen = (EditText) findViewById(R.id.edtHoTen);
        edtSDT = (EditText) findViewById(R.id.edtSDT);
    }
    public void ghiFile(String noiDung){
        try {

            if(noiDung.equals("#\n"))
            {
                Toast.makeText(getApplicationContext(),
                        "VUI LÒNG NHẬP", Toast.LENGTH_SHORT).show();
                return;
            }
            FileOutputStream outPut = openFileOutput("data.txt", Context.MODE_PRIVATE|Context.MODE_APPEND);
            byte[] epmang = noiDung.getBytes();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outPut));
            try {
                writer.write(noiDung);
                writer.flush(); // Flush the stream to ensure all data is written
                writer.close(); // Close the stream


                Toast.makeText(getApplicationContext(),
                        "Đã ghi", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "no", Toast.LENGTH_SHORT).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "not", Toast.LENGTH_SHORT).show();
        }
    }
    public void docFile(){
        try {
            FileInputStream inPut = openFileInput("data.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inPut));
            String tempStr ="";
            StringBuffer stringBuffer = new StringBuffer();
            String lastLine = "";
            while ((tempStr = reader.readLine()) != null) {
                lastLine = tempStr;
            }
            if (!lastLine.isEmpty()) {
                String[] manga = lastLine.split("#");
                Log.i("DocFile",manga[0]);
                stringBuffer.append("Tên: "+manga[0] +"\n Số điện thoại: "+manga[1]+"\n");
            }
            Toast.makeText(getApplicationContext(),
                    stringBuffer.toString(), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}