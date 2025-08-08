package com.raven.entity;

import java.util.Date;

public class Course {
    private String id;
    private String ten_khoa_hoc;
    private Date ngay_tao;

    public Course() {
    }

    public Course(String id, String ten_khoa_hoc, Date ngay_tao) {
        this.id = id;
        this.ten_khoa_hoc = ten_khoa_hoc;
        this.ngay_tao = ngay_tao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen_khoa_hoc() {
        return ten_khoa_hoc;
    }

    public void setTen_khoa_hoc(String ten_khoa_hoc) {
        this.ten_khoa_hoc = ten_khoa_hoc;
    }

    public Date getNgay_tao() {
        return ngay_tao;
    }

    public void setNgay_tao(Date ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

}
