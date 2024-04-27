package com.example.tuan12_21110940_lethanhvinh_sqlite;

public class ListviewModal {
    private int image;
    private int id;
    private String name;
    private Float diem;

    public ListviewModal() {
    }

//    public ListviewModal(int image, String name, Float diem) {
//        this.image = image;
//        this.name = name;
//        this.diem = diem;
//    }

    public ListviewModal(int image, int id, String name, Float diem) {
        this.image = image;
        this.id = id;
        this.name = name;
        this.diem = diem;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDiem() {
        return diem;
    }

    public void setDiem(Float diem) {
        this.diem = diem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
