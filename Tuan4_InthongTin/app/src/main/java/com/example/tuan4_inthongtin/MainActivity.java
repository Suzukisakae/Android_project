package com.example.tuan4_inthongtin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTen, editCMND, editBoSung;
    CheckBox chkdocsach, chkdocbao, chkdoccode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTen = (EditText) findViewById(R.id.editHoten);
        editCMND = (EditText) findViewById(R.id.editCMND);
        editBoSung = (EditText) findViewById(R.id.editBoSung);
        chkdocsach = (CheckBox) findViewById(R.id.chkdocsach);
        chkdocbao = (CheckBox) findViewById(R.id.chkdocbao);
        chkdoccode = (CheckBox) findViewById(R.id.chkdoccoding);
        Button btn = (Button) findViewById(R.id.btnguitt);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HienThi();
            }
        });
    }
    public void HienThi()
    {
        // Kiem tra ten hop le
        String ten = String.valueOf(editTen.getText());
        ten = ten.trim();
        if (ten.length() < 3)
        {
            editTen.requestFocus();
            editTen.selectAll();
            Toast.makeText(this, "Tên phải >= 3 ký tự", Toast.LENGTH_LONG).show();
            return;
        }
        // Kiem tra CMND hop le
        String cmnd = String.valueOf(editCMND.getText());
        cmnd = cmnd.trim();
        if (cmnd.length() != 9)
        {
            editCMND.requestFocus();
            editCMND.selectAll();
            Toast.makeText(this, "CMND phải đúng 9 ký tự", Toast.LENGTH_LONG).show();
            return;
        }
        // Kiem tra bang cap
        String bang = "";
        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup1);
        int id = group.getCheckedRadioButtonId();
        if (id == -1)
        {
            Toast.makeText(this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show();
            return;
        }
        RadioButton rad = (RadioButton) findViewById(id);
        bang = String.valueOf(rad.getText());
        // Kiem tra so thich
        String sothich = "";
        if (chkdocsach.isChecked())
            sothich += chkdocsach.getText() + "\n";
        if (chkdocbao.isChecked())
            sothich += chkdocbao.getText() + "\n";
        if (chkdoccode.isChecked())
            sothich += chkdoccode.getText() + "\n";
        String bosung = String.valueOf(editBoSung.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // Tạo thông tin
        String msg = ten + "\n" + cmnd + "\n" + bang + "\n" + sothich + bosung;
        builder.setMessage(msg);
        builder.create().show();
    }
}