package com.example.tuan6_21110940_quanliphongban.modal;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class PhongBan extends Infor implements Serializable{
    private static final long serialVersionUID = 1L;
    private ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
    public PhongBan(String ma, String ten) {
        super(ma, ten);
    }
    public PhongBan() {
        super();
    }
    @NonNull
    @Override
    public String toString() {
        String str = super.toString();
        if (dsnv.size()==0)
            str+= " (Chưa có nhân viên)";
        else
            str+= " (Có "+dsnv.size()+" nhân viên)";
        return str;
    }

    public void themNV(NhanVien nv){
        int i=0;
        for (; i<dsnv.size(); i++) {
            NhanVien nvOld = dsnv.get(i);
            if (nvOld.getMa().trim().equalsIgnoreCase(nv.getMa().trim()))
                break;
        }
        if (i > dsnv.size())
            dsnv.set(i,nv);
        else
            dsnv.add(nv);
    }
    public NhanVien get(int index){
        return dsnv.get(index);
    }
    public int size(){
        return dsnv.size();
    }
    public NhanVien getTruongPhong(){
        for (int i=0; i<dsnv.size(); i++) {
            NhanVien nv = dsnv.get(i);
//            if (nv.getChucVu()==ChucVu.TruongPhong)
//                return nv;
            if (nv.getChucVu().equals(ChucVu.TruongPhong))
                return nv;
        }
        return null;
    }
    public ArrayList<NhanVien> getPhoPhong() {
        ArrayList<NhanVien> dsPhoPhong = new ArrayList<NhanVien>();
        for (NhanVien nv : dsnv) {
            if (nv.getChucVu() == ChucVu.PhoPhong)
                dsPhoPhong.add(nv);
        }
        return dsPhoPhong;
    }
    public ArrayList<NhanVien> getListNhanVien() {
        return this.dsnv;
    }
}
