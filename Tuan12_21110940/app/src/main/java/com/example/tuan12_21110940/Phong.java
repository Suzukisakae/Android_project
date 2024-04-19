package com.example.tuan12_21110940;

public class Phong {
    private int id;
    private String lop;
    private String nganh;

    public Phong() {
    }

    public Phong(String lop, String nganh) {
        this.lop = lop;
        this.nganh = nganh;
    }

    public Phong(int id, String lop, String nganh) {
        this.id = id;
        this.lop = lop;
        this.nganh = nganh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }
}
