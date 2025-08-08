package com.raven.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Exam {
    private String id;
    private String tieu_de;
    private java.sql.Timestamp ngay_tao;
    private int thoi_luong;
    private int tong_so_cau;
    private Date thoi_gian_bat_dau;
    private Date thoi_gian_ket_thuc;
    private boolean kich_hoat;

    public Exam() {
    }

    public Exam(String id, String tieu_de, Timestamp ngay_tao, int thoi_luong, int tong_so_cau, Date thoi_gian_bat_dau, Date thoi_gian_ket_thuc, boolean kich_hoat) {
        this.id = id;
        this.tieu_de = tieu_de;
        this.ngay_tao = ngay_tao;
        this.thoi_luong = thoi_luong;
        this.tong_so_cau = tong_so_cau;
        this.thoi_gian_bat_dau = thoi_gian_bat_dau;
        this.thoi_gian_ket_thuc = thoi_gian_ket_thuc;
        this.kich_hoat = kich_hoat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTieu_de() {
        return tieu_de;
    }

    public void setTieu_de(String tieu_de) {
        this.tieu_de = tieu_de;
    }

    public Timestamp getNgay_tao() {
        return ngay_tao;
    }

    public void setNgay_tao(Timestamp ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public int getThoi_luong() {
        return thoi_luong;
    }

    public void setThoi_luong(int thoi_luong) {
        this.thoi_luong = thoi_luong;
    }

    public int getTong_so_cau() {
        return tong_so_cau;
    }

    public void setTong_so_cau(int tong_so_cau) {
        this.tong_so_cau = tong_so_cau;
    }

    public Date getThoi_gian_bat_dau() {
        return thoi_gian_bat_dau;
    }

    public void setThoi_gian_bat_dau(Date thoi_gian_bat_dau) {
        this.thoi_gian_bat_dau = thoi_gian_bat_dau;
    }

    public Date getThoi_gian_ket_thuc() {
        return thoi_gian_ket_thuc;
    }

    public void setThoi_gian_ket_thuc(Date thoi_gian_ket_thuc) {
        this.thoi_gian_ket_thuc = thoi_gian_ket_thuc;
    }

    public boolean isKich_hoat() {
        return kich_hoat;
    }

    public void setKich_hoat(boolean kich_hoat) {
        this.kich_hoat = kich_hoat;
    }
}
