package com.example.tuan13_21110940_lethanhvinh_firebase;

public class SinhVien {
    private int id;
    private String name;
    private float diem;
    private String monhoc;

    public SinhVien() {
    }

    public SinhVien(int id, String name, float diem, String monhoc) {
        this.id = id;
        this.name = name;
        this.diem = diem;
        this.monhoc = monhoc;
    }

    public SinhVien(String name, float diem, String monhoc) {
        this.name = name;
        this.diem = diem;
        this.monhoc = monhoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }

    public String getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(String monhoc) {
        this.monhoc = monhoc;
    }
}
