/*
 * Nguoi viet: Le Thanh Vinh
 * MSSV: 21110940
 * Bai 5: Xay dung ung dung quan ly danh sach nhan vien
 * */
package com.example.tuan5_21110940_bai4;

public class Employee {
    private String id;
    private String name;
    private Boolean gender;

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
    public boolean isGender(){
        return gender;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.name + " - ";
    }
}
