package com.example.tuan12_21110940;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelperPhong extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tuan12";
    private static final String TABLE_NAME = "tuan12";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LOP = "lop";
    private static final String COLUMN_NGANH = "nganh";

    public SQLiteHelperPhong(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LOP + " TEXT, " + COLUMN_NGANH + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addPhong(Phong phong) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_LOP + ", " + COLUMN_NGANH + ") VALUES ('" + phong.getLop() + "', '" + phong.getNganh() + "')";
        db.execSQL(query);
        db.close();
    }

    public void deletePhong(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id;
        db.execSQL(query);
        db.close();
    }

    public void deletePhong(Phong phong) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + phong.getId();
        db.execSQL(query);
        db.close();
    }

    public void deleteAllPhong() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME;
        db.execSQL(query);
        db.close();
    }

    public void updatePhong(Phong phong) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COLUMN_LOP + " = '" + phong.getLop() + "', " + COLUMN_NGANH + " = '" + phong.getNganh() + "' WHERE " + COLUMN_ID + " = " + phong.getId();
        db.execSQL(query);
        db.close();
    }
//    public void updatePhong(Phong phong) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        // Update phong trong database co cùng Lop
//        String query = "UPDATE " + TABLE_NAME + " SET " + COLUMN_NGANH + " = '" + phong.getNganh() + "' WHERE " + COLUMN_LOP + " = '" + phong.getLop() + "'";
//        db.execSQL(query);
//        db.close();
//    }

    public Phong getPhong(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id;
        Phong phong = new Phong();
        db.close();
        return phong;
    }

    public List<Phong> getAllPhong() {
        List<Phong> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Phong phong = new Phong();
                phong.setId(cursor.getInt(0));
                phong.setLop(cursor.getString(1));
                phong.setNganh(cursor.getString(2));
                list.add(phong);
            } while (cursor.moveToNext());
        }
        db.close();
        return list;
    }

}
