/*
* Hoj và tên: Lê Thành Vinh
* Mã sinh viên: 21110940
* Bài tập: Tạo listview cơ bản
**/

package com.example.a21110940_tuan4_listviewcoban;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class SanPham implements Serializable {
    private String tenSp;
    private String mauSac;

    public SanPham() {
    }

    public SanPham(String tenSp, String mauSac) {
        this.tenSp = tenSp;
        this.mauSac = mauSac;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    @NonNull
    @Override
    public String toString() {
        return this.tenSp + " \n " + this.mauSac;
    }
}
