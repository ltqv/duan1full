package com.raven.entity;

public class ViewedSubmission {
    private int id;
    private String id_hocvien;
    private String id_bailam;

    public ViewedSubmission() {
    }

    public ViewedSubmission(int id, String id_hocvien, String id_bailam) {
        this.id = id;
        this.id_hocvien = id_hocvien;
        this.id_bailam = id_bailam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_hocvien() {
        return id_hocvien;
    }

    public void setId_hocvien(String id_hocvien) {
        this.id_hocvien = id_hocvien;
    }

    public String getId_bailam() {
        return id_bailam;
    }

    public void setId_bailam(String id_bailam) {
        this.id_bailam = id_bailam;
    }

    
}
