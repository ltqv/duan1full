package com.raven.entity;

import java.util.Date;

public class Classroom {
    private String id;
    private String id_khoa_hoc;
    private String hoc_ky;
    private int tong_buoi;
    private String id_giang_vien;
    private Date ngay_bat_dau;
    private Date ngay_ket_thuc;

    public Classroom() {
    }

    public Classroom(String id, String id_khoa_hoc, String hoc_ky, int tong_buoi, String id_giang_vien, Date ngay_bat_dau, Date ngay_ket_thuc) {
        this.id = id;
        this.id_khoa_hoc = id_khoa_hoc;
        this.hoc_ky = hoc_ky;
        this.tong_buoi = tong_buoi;
        this.id_giang_vien = id_giang_vien;
        this.ngay_bat_dau = ngay_bat_dau;
        this.ngay_ket_thuc = ngay_ket_thuc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_khoa_hoc() {
        return id_khoa_hoc;
    }

    public void setId_khoa_hoc(String id_khoa_hoc) {
        this.id_khoa_hoc = id_khoa_hoc;
    }

    public String getHoc_ky() {
        return hoc_ky;
    }

    public void setHoc_ky(String hoc_ky) {
        this.hoc_ky = hoc_ky;
    }

    public int getTong_buoi() {
        return tong_buoi;
    }

    public void setTong_buoi(int tong_buoi) {
        this.tong_buoi = tong_buoi;
    }

    public String getId_giang_vien() {
        return id_giang_vien;
    }

    public void setId_giang_vien(String id_giang_vien) {
        this.id_giang_vien = id_giang_vien;
    }

    public Date getNgay_bat_dau() {
        return ngay_bat_dau;
    }

    public void setNgay_bat_dau(Date ngay_bat_dau) {
        this.ngay_bat_dau = ngay_bat_dau;
    }

    public Date getNgay_ket_thuc() {
        return ngay_ket_thuc;
    }

    public void setNgay_ket_thuc(Date ngay_ket_thuc) {
        this.ngay_ket_thuc = ngay_ket_thuc;
    }

    
}
