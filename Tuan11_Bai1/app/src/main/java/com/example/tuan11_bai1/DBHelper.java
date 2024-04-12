package com.example.tuan11_bai1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHelper {
    String DATABASE_NAME = "SinhVien_DB.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database = null;
    Context context;

    public DBHelper(Context context) {
        this.context = context;
        progressSQLite();
    }

    private void progressSQLite() {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                CopyDatabaseFromAsset();
                Toast.makeText(context, "Copying of database is successful", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void CopyDatabaseFromAsset() {
        try {
            InputStream databaseInputStream = context.getAssets().open(DATABASE_NAME);
            String outputFileName = getPathDatabaseSystem();
            File file = new File(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!file.exists()) {
                file.mkdir();
            }
            OutputStream databaseOutputStream = new FileOutputStream(outputFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = databaseInputStream.read(buffer)) > 0) {
                databaseOutputStream.write(buffer, 0, length);
            }
            databaseOutputStream.flush();
            databaseOutputStream.close();
            databaseInputStream.close();
        } catch (Exception ex) {
            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private String getPathDatabaseSystem() {
        return context.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    public ArrayList<SinhVien> getAllSinhVien() {
        ArrayList<SinhVien> list = new ArrayList<>();
        database = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        String sql = "SELECT * FROM tb_SinhVien";
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String diaChi = cursor.getString(2);
            int gioiTinh = cursor.getInt(3);
            list.add(new SinhVien(ma, ten, diaChi, gioiTinh));
        }
        return list;
    }

    public void themSinhVien(SinhVien sv) {
        database = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        ContentValues contentValues = new ContentValues();
        contentValues.put("ma", sv.getMa());
        contentValues.put("ten", sv.getTen());
        contentValues.put("diaChi", sv.getDiaChi());
        contentValues.put("gioiTinh", sv.getGioiTinh());

        if (database.insert("tb_SinhVien", null, contentValues) > 0) {
            Toast.makeText(context, "Insert successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Insert failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void capNhatSinhVien(SinhVien sv) {
        database = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        ContentValues contentValues = new ContentValues();
        contentValues.put("ma", sv.getMa());
        contentValues.put("ten", sv.getTen());
        contentValues.put("diaChi", sv.getDiaChi());
        contentValues.put("gioiTinh", sv.getGioiTinh());

        if (database.update("tb_SinhVien", contentValues, "ma=" + sv.getMa(), null) > 0) {
            Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Update failed", Toast.LENGTH_SHORT).show();
        }
    }
    public void xoaSinhVien(SinhVien sv) {
        database = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        if (database.delete("tb_SinhVien", "ma=" + sv.getMa(), null) > 0) {
            Toast.makeText(context, "Delete successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Delete failed", Toast.LENGTH_SHORT).show();
        }
    }
}
