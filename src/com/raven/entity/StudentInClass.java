package com.raven.entity;

public class StudentInClass {
    private int id;
    private String id_lop;
    private String id_hoc_vien;
    private String trang_thai;

    public StudentInClass() {
    }

    public StudentInClass(int id, String id_lop, String id_hoc_vien, String trang_thai) {
        this.id = id;
        this.id_lop = id_lop;
        this.id_hoc_vien = id_hoc_vien;
        this.trang_thai = trang_thai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_lop() {
        return id_lop;
    }

    public void setId_lop(String id_lop) {
        this.id_lop = id_lop;
    }

    public String getId_hoc_vien() {
        return id_hoc_vien;
    }

    public void setId_hoc_vien(String id_hoc_vien) {
        this.id_hoc_vien = id_hoc_vien;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    
}
