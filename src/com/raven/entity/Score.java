package com.raven.entity;

import java.util.Date;

public class Score {
    private String id;
    private String id_bai_lam;
    private double diem;
    private String nhan_xet;
    private Date ngay_cham;

    public Score() {
    }

    public Score(String id, String id_bai_lam, double diem, String nhan_xet, Date ngay_cham) {
        this.id = id;
        this.id_bai_lam = id_bai_lam;
        this.diem = diem;
        this.nhan_xet = nhan_xet;
        this.ngay_cham = ngay_cham;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_bai_lam() {
        return id_bai_lam;
    }

    public void setId_bai_lam(String id_bai_lam) {
        this.id_bai_lam = id_bai_lam;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    public String getNhan_xet() {
        return nhan_xet;
    }

    public void setNhan_xet(String nhan_xet) {
        this.nhan_xet = nhan_xet;
    }

    public Date getNgay_cham() {
        return ngay_cham;
    }

    public void setNgay_cham(Date ngay_cham) {
        this.ngay_cham = ngay_cham;
    }
    
    
}
