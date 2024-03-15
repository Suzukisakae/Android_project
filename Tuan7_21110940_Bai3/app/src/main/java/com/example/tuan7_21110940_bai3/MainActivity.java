/*
 * Nguoi viet: Le Thanh Vinh
 * MSSV: 21110940
 * Mo ta: Bai 3
 * */
package com.example.tuan7_21110940_bai3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    enum ToanTu {
        Cong, Tru, Nhan, Chia
    }

    Button btncong, btntru, btnnhan, btnchia;
    EditText editsoa, editsob;
    TextView txtkq;
    ListView lvHistory;
    ArrayList<String> arr_toantu = new ArrayList<String>();
    ArrayAdapter<String> adapter = null;

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.btncong: {
//                    processToanTu(ToanTu.Cong);
//                }
//                break;
//                case R.id.btntru: {
//                    processToanTu(ToanTu.Tru);
//                }
//                break;
//                case R.id.btnnhan: {
//                    processToanTu(ToanTu.Nhan);
//                }
//                break;
//                case R.id.btnchia: {
//                    processToanTu(ToanTu.Chia);
//                }
//                break;
//            }
            if (v.getId() == R.id.btncong) {
                processToanTu(ToanTu.Cong);
            } else if (v.getId() == R.id.btntru) {
                processToanTu(ToanTu.Tru);
            } else if (v.getId() == R.id.btnnhan) {
                processToanTu(ToanTu.Nhan);
            } else if (v.getId() == R.id.btnchia) {
                processToanTu(ToanTu.Chia);
            }
        }
    };

    public void processToanTu(ToanTu tt) {
        String sa = editsoa.getText() + "";
        String sb = editsob.getText().toString();
        int a = Integer.parseInt(sa);
        int b = Integer.parseInt(sb);
        String kq = "";
        switch (tt)
        {
            case Cong:
                kq = a + " + " + b + " = " + (a + b);
                break;
            case Tru:
                kq = a + " - " + b + " = " + (a - b);
                break;
            case Nhan:
                kq = a + " * " + b + " = " + (a * b);
                break;
            case Chia:
                if (b == 0) {
                    kq = "Khong the chia cho 0";
                } else {
                    kq = a + " / " + b + " = " + (a / b);
                }
                break;
            default:
                kq = "Toan tu khong hop le";
        }
        editsoa.setText("");
        editsob.setText("");
        editsoa.requestFocus();
        txtkq.setText(kq);
        arr_toantu.add(kq);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadTabs();
        doFormWidgets();
    }
    public void loadTabs() {
        // Lay tabHost id ra truoc
        final TabHost tab = (TabHost) findViewById(R.id.tabHost);
        // goi lenh setup
        tab.setup();
        TabHost.TabSpec spec;
        // Tao tab1
        spec = tab.newTabSpec("t1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("1 - Tinh toan");
        tab.addTab(spec);
        // Tao tab2
        spec = tab.newTabSpec("t2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("2 - Nhat ky");
        tab.addTab(spec);
        // Thiet lap tab mac dinh
        tab.setCurrentTab(0);
        // Vi du tab1 chua nhap trong tin xong ma lai qua tab 2 thi bao
        tab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                String s = "Tab ID: " + tabId + " - Index: " + tab.getCurrentTab();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void doFormWidgets()
    {
        btncong = (Button) findViewById(R.id.btncong);
        btntru = (Button) findViewById(R.id.btntru);
        btnnhan = (Button) findViewById(R.id.btnnhan);
        btnchia = (Button) findViewById(R.id.btnchia);
        editsoa = (EditText) findViewById(R.id.editsoa);
        editsob = (EditText) findViewById(R.id.editsob);
        txtkq = (TextView) findViewById(R.id.txtketqua);
        lvHistory = (ListView) findViewById(R.id.lvhistory);
        btncong.setOnClickListener(myClick);
        btntru.setOnClickListener(myClick);
        btnnhan.setOnClickListener(myClick);
        btnchia.setOnClickListener(myClick);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_toantu);
        lvHistory.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }
}