package com.example.tuan6_21110940_quanliphongban.modal;

public enum ChucVu {
    TruongPhong("Trưởng phòng"),
    PhoPhong("Phó phòng"),
    NhanVien("Nhân viên");
    private String cv;
    ChucVu(String cv) {
        this.cv = cv;
    }
    public String getChucVu() {
        return cv;
    }
}
