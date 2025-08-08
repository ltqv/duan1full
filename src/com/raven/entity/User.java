package com.raven.entity;

import java.sql.Timestamp;
import java.util.Date;

public class User {
    private String id;
    private String ten_dang_nhap;
    private String mat_khau;
    private String ho_ten;
    private String so_dien_thoai;
    private String email;
    private String vai_tro;
    private Date ngay_tao;

    public User() {
    }

    public User(String id, String ten_dang_nhap, String mat_khau, String ho_ten, String so_dien_thoai, String email, String vai_tro, Timestamp ngay_tao) {
        this.id = id;
        this.ten_dang_nhap = ten_dang_nhap;
        this.mat_khau = mat_khau;
        this.ho_ten = ho_ten;
        this.so_dien_thoai = so_dien_thoai;
        this.email = email;
        this.vai_tro = vai_tro;
        this.ngay_tao = ngay_tao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen_dang_nhap() {
        return ten_dang_nhap;
    }

    public void setTen_dang_nhap(String ten_dang_nhap) {
        this.ten_dang_nhap = ten_dang_nhap;
    }

    public String getMat_khau() {
        return mat_khau;
    }

    public void setMat_khau(String mat_khau) {
        this.mat_khau = mat_khau;
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

    public Date getNgay_tao() {
        return ngay_tao;
    }

    public void setNgay_tao(Date ngay_tao) {
        this.ngay_tao = ngay_tao;
    }    
}
