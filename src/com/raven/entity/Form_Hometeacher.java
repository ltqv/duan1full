package com.raven.entity;

public class Form_Hometeacher {
    private String id_nguoi_dung;
    private String ho_ten;
    private String so_dien_thoai;
    private String email;
    private String vai_tro;
    private String id_lop;
    private String id_hoc_vien;

    public Form_Hometeacher() {
    }

    public Form_Hometeacher(String id_nguoi_dung, String ho_ten, String so_dien_thoai, String email, String vai_tro, String id_lop, String id_hoc_vien) {
        this.id_nguoi_dung = id_nguoi_dung;
        this.ho_ten = ho_ten;
        this.so_dien_thoai = so_dien_thoai;
        this.email = email;
        this.vai_tro = vai_tro;
        this.id_lop = id_lop;
        this.id_hoc_vien = id_hoc_vien;
    }

    public String getId_nguoi_dung() {
        return id_nguoi_dung;
    }

    public void setId_nguoi_dung(String id_nguoi_dung) {
        this.id_nguoi_dung = id_nguoi_dung;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVai_tro() {
        return vai_tro;
    }

    public void setVai_tro(String vai_tro) {
        this.vai_tro = vai_tro;
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

    
    
}
