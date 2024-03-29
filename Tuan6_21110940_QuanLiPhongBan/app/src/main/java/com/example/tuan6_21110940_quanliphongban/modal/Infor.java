package com.example.tuan6_21110940_quanliphongban.modal;

import java.io.Serializable;

public class Infor implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ma;
    private String ten;

    public Infor(String ma, String ten) {
        super();
        this.ma = ma;
        this.ten = ten;
    }
    public Infor() {
        super();
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
