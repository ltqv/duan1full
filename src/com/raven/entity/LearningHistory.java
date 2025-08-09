package com.raven.entity;

import java.util.Date;

public class LearningHistory {

    private int id;
    private String id_hoc_vien;
    private String id_lop;
    private String id_lich_hoc;
    private Date ngay_hoc_thuc_te;
    private boolean co_mat;
    private String ghi_chu;

    public LearningHistory() {
    }

    public LearningHistory(int id, String id_hoc_vien, String id_lop, String id_lich_hoc, Date ngay_hoc_thuc_te, boolean co_mat, String ghi_chu) {
        this.id = id;
        this.id_hoc_vien = id_hoc_vien;
        this.id_lop = id_lop;
        this.id_lich_hoc = id_lich_hoc;
        this.ngay_hoc_thuc_te = ngay_hoc_thuc_te;
        this.co_mat = co_mat;
        this.ghi_chu = ghi_chu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_hoc_vien() {
        return id_hoc_vien;
    }

    public void setId_hoc_vien(String id_hoc_vien) {
        this.id_hoc_vien = id_hoc_vien;
    }

    public String getId_lop() {
        return id_lop;
    }

    public void setId_lop(String id_lop) {
        this.id_lop = id_lop;
    }

    public String getId_lich_hoc() {
        return id_lich_hoc;
    }

    public void setId_lich_hoc(String id_lich_hoc) {
        this.id_lich_hoc = id_lich_hoc;
    }

    public Date getNgay_hoc_thuc_te() {
        return ngay_hoc_thuc_te;
    }

    public void setNgay_hoc_thuc_te(Date ngay_hoc_thuc_te) {
        this.ngay_hoc_thuc_te = ngay_hoc_thuc_te;
    }

    public boolean getCo_mat() {
        return co_mat;
    }

    public void setCo_mat(boolean co_mat) {
        this.co_mat = co_mat;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    
    
}
