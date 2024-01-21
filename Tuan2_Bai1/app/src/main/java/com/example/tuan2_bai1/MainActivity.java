package com.example.tuan2_bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editTextNumber1 = (EditText) findViewById(R.id.editTextNumber1);
        final EditText editTextNumber2 = (EditText) findViewById(R.id.editTextNumber2);

        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                double number1 = Double.parseDouble(editTextNumber1.getText().toString());
                double number2 = Double.parseDouble(editTextNumber2.getText().toString());
                double sum = number1 + number2;

                Toast toast = Toast.makeText(MainActivity.this, "Sum of two number is " + sum, Toast.LENGTH_LONG);
                toast.show();
                } catch (Exception e) {
                    Toast toast = Toast.makeText(MainActivity.this, "Please enter number", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        }

}