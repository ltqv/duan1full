package com.raven.entity;

import java.util.Date;

public class Submission {
    private String id;
    private String id_giang_vien;
    private String id_bai_kt;
    private Date thoi_gian_nop;
    private String ket_qua_json;
    private int so_cau_dung;

    public Submission() {
    }

    public Submission(String id, String id_giang_vien, String id_bai_kt, Date thoi_gian_nop, String ket_qua_json, int so_cau_dung) {
        this.id = id;
        this.id_giang_vien = id_giang_vien;
        this.id_bai_kt = id_bai_kt;
        this.thoi_gian_nop = thoi_gian_nop;
        this.ket_qua_json = ket_qua_json;
        this.so_cau_dung = so_cau_dung;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_giang_vien() {
        return id_giang_vien;
    }

    public void setId_giang_vien(String id_giang_vien) {
        this.id_giang_vien = id_giang_vien;
    }

    public String getId_bai_kt() {
        return id_bai_kt;
    }

    public void setId_bai_kt(String id_bai_kt) {
        this.id_bai_kt = id_bai_kt;
    }

    public Date getThoi_gian_nop() {
        return thoi_gian_nop;
    }

    public void setThoi_gian_nop(Date thoi_gian_nop) {
        this.thoi_gian_nop = thoi_gian_nop;
    }

    public String getKet_qua_json() {
        return ket_qua_json;
    }

    public void setKet_qua_json(String ket_qua_json) {
        this.ket_qua_json = ket_qua_json;
    }

    public int getSo_cau_dung() {
        return so_cau_dung;
    }

    public void setSo_cau_dung(int so_cau_dung) {
        this.so_cau_dung = so_cau_dung;
    }

    
}
