package com.raven.entity;

import java.sql.Time;

public class Schedule {
    private String id;
    private String id_lop;
    private int thu_trong_tuan;
    private java.sql.Time gio_bat_dau;
    private java.sql.Time gio_ket_thuc;
    private String phong_hoc;

    public Schedule() {
    }

    public Schedule(String id, String id_lop, int thu_trong_tuan, Time gio_bat_dau, Time gio_ket_thuc, String phong_hoc) {
        this.id = id;
        this.id_lop = id_lop;
        this.thu_trong_tuan = thu_trong_tuan;
        this.gio_bat_dau = gio_bat_dau;
        this.gio_ket_thuc = gio_ket_thuc;
        this.phong_hoc = phong_hoc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_lop() {
        return id_lop;
    }

    public void setId_lop(String id_lop) {
        this.id_lop = id_lop;
    }

    public int getThu_trong_tuan() {
        return thu_trong_tuan;
    }

    public void setThu_trong_tuan(int thu_trong_tuan) {
        this.thu_trong_tuan = thu_trong_tuan;
    }

    public Time getGio_bat_dau() {
        return gio_bat_dau;
    }

    public void setGio_bat_dau(Time gio_bat_dau) {
        this.gio_bat_dau = gio_bat_dau;
    }

    public Time getGio_ket_thuc() {
        return gio_ket_thuc;
    }

    public void setGio_ket_thuc(Time gio_ket_thuc) {
        this.gio_ket_thuc = gio_ket_thuc;
    }

    public String getPhong_hoc() {
        return phong_hoc;
    }

    public void setPhong_hoc(String phong_hoc) {
        this.phong_hoc = phong_hoc;
    }

    
}
