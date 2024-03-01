/*
 * Người viết: Lê Thành Vinh
 * MSSV: 21110940
 * Nội dung: Bài 1 - Cập nhật sinh viên
 * */
package com.example.tuan5_21110940_bai1;
import java.util.Date;

public class Student {
    private String id;
    private String name;
    private Boolean gender;
    private Date ngaysinh;
    private String noiSinh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
    }

    public Student() {
    }

    public Student(String id, String name, Boolean gender, Date ngaysinh, String noiSinh) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.ngaysinh = ngaysinh;
        this.noiSinh = noiSinh;
    }
}
