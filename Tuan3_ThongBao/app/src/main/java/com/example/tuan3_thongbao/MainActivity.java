package com.example.tuan3_thongbao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonDialog, listDialog, radioDialog, checkboxDialog, progressDialog, progressBar, customDialog;
    TextView textDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonDialog = (Button) findViewById(R.id.btnDialog);
        listDialog = (Button) findViewById(R.id.btnListDialog);
        radioDialog = (Button) findViewById(R.id.btnRadioDialog);
        checkboxDialog = (Button) findViewById(R.id.btnCheckBoxDialog);
        progressDialog = (Button) findViewById(R.id.btnProgressDialog);
        progressBar = (Button) findViewById(R.id.btnProgressBarDialog);
        customDialog = (Button) findViewById(R.id.btnCustomDialog);
        textDisplay = (TextView) findViewById(R.id.txtDisplay);

        buttonDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuliNutDialog();
            }
        });
        listDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuliNutListDialog();
            }
        });
        radioDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuliNutRadioDialog();
            }
        });
        checkboxDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuliNutCheckBoxDialog();
            }
        });
        progressDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuliNutProgressDialog();
            }
        });
        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuliNutProgressBarDialog();
            }
        });
        customDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuliNutCustomDialog();
            }
        });
    }
//    mang chuoi sap xep tuan tu
    final CharSequence[] items = {"Android", "Java", "PHP"};
//    mang chua trang thai cac item
    final boolean[] states = {false, false, false};

    Handler handler = new Handler();
    private void xuliNutDialog() {
        textDisplay.setText("");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thoat");
        builder.setIcon(R.drawable.dancing_oshi_no_ko);
        builder.setMessage("Ban co muon thoat khong?");
        builder.setCancelable(false);
        builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textDisplay.setText("Ban da chon co");
            }
        });
        builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textDisplay.setText("Ban da chon khong");
            }
        });
        builder.show();
    }

    private void xuliNutListDialog() {
        textDisplay.setText("");
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Chon ngon ngu lap trinh");
        builder1.setIcon(R.drawable.dancing_oshi_no_ko);
        builder1.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                textDisplay.setText("Ban da chon: " + items[item].toString());
            }
        });
        builder1.show();
    }

    private void xuliNutRadioDialog() {
        textDisplay.setText("");
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("Chon ngon ngu lap trinh");
        builder2.setIcon(R.drawable.dancing_oshi_no_ko);
        builder2.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
                textDisplay.setText("Ban da chon: " + items[item].toString());
//                dialog.dismiss();
            }
        });
        builder2.show();
    }

    private void xuliNutCheckBoxDialog() {
        textDisplay.setText("");
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
        builder3.setTitle("Chon ngon ngu lap trinh");
        builder3.setIcon(R.drawable.dancing_oshi_no_ko);
        builder3.setMultiChoiceItems(items, states, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item, boolean isChecked) {
                Toast.makeText(getApplicationContext(), items[item] + " duoc gan la " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        builder3.show();
    }

    private void xuliNutProgressDialog() {
        textDisplay.setText("");
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIcon(R.drawable.dancing_oshi_no_ko);
        progressDialog.setTitle("Dang xu ly");
        progressDialog.setMessage("Vui long cho...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setButton(ProgressDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textDisplay.setText("Ban da chon OK");
            }
        });
        progressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textDisplay.setText("Ban da chon Cancel");
            }
        });
        progressDialog.show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        }, 5000);
    }

    private void xuliNutProgressBarDialog() {
        textDisplay.setText("");
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIcon(R.drawable.dancing_oshi_no_ko);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Dang xu ly");
        progressDialog.setMessage("Vui long cho...");
        progressDialog.setCancelable(true);
        progressDialog.setProgress(0);
        progressDialog.setMax(100);
        progressDialog.show();

        // Tao mot thread de chay tien trinh
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress < 100) {
                    try {
                        Thread.sleep(200);
                        progress++;
                        progressDialog.setProgress(progress);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                progressDialog.dismiss();
            }
        }).start();
    }

    private void xuliNutCustomDialog() {
        textDisplay.setText("");
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.customdialog);
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(true);

        ImageView imageView = (ImageView) dialog.findViewById(R.id.imgAndroid);
        imageView.setImageResource(R.drawable.dancing_oshi_no_ko);

        TextView txtContent = (TextView) dialog.findViewById(R.id.txtContext);
        txtContent.setText("Day la custom dialog");

        dialog.show();

    }

}