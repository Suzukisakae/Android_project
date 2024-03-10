package com.example.tuan6_21110940_quanliphongban.modal;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class NhanVien extends Infor implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean gioiTinh;
    private ChucVu chucVu;
    private PhongBan phongBan;
    public NhanVien(String ma, String ten, boolean gioiTinh, ChucVu chucVu, PhongBan phongBan) {
        super(ma, ten);
        this.gioiTinh = gioiTinh;
        this.chucVu = chucVu;
        this.phongBan = phongBan;
    }
    public NhanVien() {
        super();
    }
    public NhanVien(String ma, String ten, boolean gioiTinh) {
        super(ma, ten);
        this.gioiTinh = gioiTinh;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    public PhongBan getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
    }
}
