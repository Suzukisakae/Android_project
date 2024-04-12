package com.example.tuan11_bai1;

public class SinhVien {
    int ma;
    String ten;
    String diaChi;
    int gioiTinh;

    public SinhVien() {
    }

    public SinhVien(int ma, String ten, String diaChi, int gioiTinh) {
        this.ma = ma;
        this.ten = ten;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
}
