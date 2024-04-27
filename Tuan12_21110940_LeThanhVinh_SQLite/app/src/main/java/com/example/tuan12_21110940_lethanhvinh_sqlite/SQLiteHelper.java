package com.example.tuan12_21110940_lethanhvinh_sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tuan13";
    private static final String TABLE_NAME = "quanlysinhvien";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DIEM = "diem";
    private static final String COLUMN_MONHOC = "monhoc";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DIEM + " FLOAT,"
                + COLUMN_MONHOC + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void addSinhVien(SinhVien sinhVien){
        SQLiteDatabase db = this.getWritableDatabase();
        String INSERT = "INSERT INTO " + TABLE_NAME + "(" + COLUMN_NAME + "," + COLUMN_DIEM + "," + COLUMN_MONHOC + ") VALUES('" + sinhVien.getName() + "'," + sinhVien.getDiem() + ",'" + sinhVien.getMonhoc() + "')";
        db.execSQL(INSERT);
        db.close();
    }
    public void updateSinhVien(SinhVien sinhVien){
        SQLiteDatabase db = this.getWritableDatabase();
        String UPDATE = "UPDATE " + TABLE_NAME + " SET " + COLUMN_NAME + " = '" + sinhVien.getName() + "'," + COLUMN_DIEM + " = " + sinhVien.getDiem() + "," + COLUMN_MONHOC + " = '" + sinhVien.getMonhoc() + "' WHERE " + COLUMN_ID + " = " + sinhVien.getId();
        db.execSQL(UPDATE);
        db.close();
    }
    public void deleteSinhVien(SinhVien sinhVien){
        SQLiteDatabase db = this.getWritableDatabase();
        String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + sinhVien.getId();
        db.execSQL(DELETE);
        db.close();
    }
    public void deleteAllSinhVien(){
        SQLiteDatabase db = this.getWritableDatabase();
        String DELETE = "DELETE FROM " + TABLE_NAME;
        db.execSQL(DELETE);
        db.close();
    }
    public List<SinhVien> getAllSinhVien(){
        List<SinhVien> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                SinhVien sinhVien = new SinhVien();
                sinhVien.setId(cursor.getInt(0));
                sinhVien.setName(cursor.getString(1));
                sinhVien.setDiem(cursor.getFloat(2));
                sinhVien.setMonhoc(cursor.getString(3));
                list.add(sinhVien);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
//    Lấy ra id lớn nhất
    public int getMaxID(){
        int maxID = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT MAX(" + COLUMN_ID + ") FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            maxID = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return maxID;
    }

}
