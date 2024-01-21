package com.example.tuan2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editTextYear;
    private Button buttonCovert;
    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextYear = (EditText) findViewById(R.id.editTextYear);
        buttonCovert = (Button) findViewById(R.id.buttonCovert);
        textViewResult = (TextView) findViewById(R.id.textViewResult);

        buttonCovert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertLunarYear();
            }
        });

    }
    private void convertLunarYear() {
        try {
            int year = Integer.parseInt(editTextYear.getText().toString());
            String result = convertToLunar(year);
            textViewResult.setText("Nam Âm lich: " + result);
        }
        catch (Exception e) {
            textViewResult.setText("Please enter number");
        }
    }
    private String convertToLunar(int year) {
        // CÔng thức tính năm âm lịch
        int offset = year - 1900;
        int lunarYear = 6; // Năm 1900 là Canh Tý

        for (int i = 0; i < offset; i++) {
            lunarYear = (lunarYear + 1) % 10;
        }

        // Tính can chi
        String can = getCan (lunarYear) ;
        String chi = getChi (year) ;

        return lunarYear + " " + can + " " + chi;
    }
    private String getCan(int year) {
        switch (year) {
            case 0: return "Giáp";
            case 1: return "Ất";
            case 2: return "Bính";
            case 3: return "Đinh";
            case 4: return "Mậu";
            case 5: return "Kỷ";
            case 6: return "Canh";
            case 7: return "Tân";
            case 8: return "Nhâm";
            case 9: return "Quý";
        }
        return "";
    }
    private String getChi(int year) {
        switch (year % 12) {
            case 0: return "Thân";
            case 1: return "Dậu";
            case 2: return "Tuất";
            case 3: return "Hợi";
            case 4: return "Tý";
            case 5: return "Sửu";
            case 6: return "Dần";
            case 7: return "Mẹo";
            case 8: return "Thìn";
            case 9: return "Tỵ";
            case 10: return "Ngọ";
            case 11: return "Mùi";
        }
        return "";
    }
}