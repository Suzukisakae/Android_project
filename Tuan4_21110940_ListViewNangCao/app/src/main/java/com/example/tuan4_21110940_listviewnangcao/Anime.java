/*
 * Người viết: Lê THành Vinh
 * Mã số sinh viên: 21110940
 * Bài tập tuần 4 - Ứng dụng ListView nâng cao
 * */

package com.example.tuan4_21110940_listviewnangcao;

import java.io.Serializable;

public class Anime implements Serializable {
    private String ten;
    private String like;
    private int hinh;

    public Anime() {
    }

    public Anime(String ten, String like, int hinh) {
        this.ten = ten;
        this.like = like;
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }
}
