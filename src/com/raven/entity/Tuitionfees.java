
package com.raven.entity;

import java.math.BigDecimal;
import java.util.Date;


public class Tuitionfees {
     private int id_hoc_phi;
    private String id_hoc_vien;
    private String hoc_ky;
    private String nam_hoc;
    private BigDecimal tong_hoc_phi;
    private BigDecimal da_dong;
    private BigDecimal con_lai;
    private String trang_thai; // 'DA_DONG' hoặc 'CON_NO'
    private Date ngay_dong_cuoi;

    public Tuitionfees() {
    }

    public Tuitionfees(int id_hoc_phi, String id_hoc_vien, String hoc_ky, String nam_hoc, BigDecimal tong_hoc_phi, BigDecimal da_dong, BigDecimal con_lai, String trang_thai, Date ngay_dong_cuoi) {
        this.id_hoc_phi = id_hoc_phi;
        this.id_hoc_vien = id_hoc_vien;
        this.hoc_ky = hoc_ky;
        this.nam_hoc = nam_hoc;
        this.tong_hoc_phi = tong_hoc_phi;
        this.da_dong = da_dong;
        this.con_lai = con_lai;
        this.trang_thai = trang_thai;
        this.ngay_dong_cuoi = ngay_dong_cuoi;
    }

    public int getId_hoc_phi() {
        return id_hoc_phi;
    }

    public void setId_hoc_phi(int id_hoc_phi) {
        this.id_hoc_phi = id_hoc_phi;
    }

    public String getId_hoc_vien() {
        return id_hoc_vien;
    }

    public void setId_hoc_vien(String id_hoc_vien) {
        this.id_hoc_vien = id_hoc_vien;
    }

    public String getHoc_ky() {
        return hoc_ky;
    }

    public void setHoc_ky(String hoc_ky) {
        this.hoc_ky = hoc_ky;
    }

    public String getNam_hoc() {
        return nam_hoc;
    }

    public void setNam_hoc(String nam_hoc) {
        this.nam_hoc = nam_hoc;
    }

    public BigDecimal getTong_hoc_phi() {
        return tong_hoc_phi;
    }

    public void setTong_hoc_phi(BigDecimal tong_hoc_phi) {
        this.tong_hoc_phi = tong_hoc_phi;
    }

    public BigDecimal getDa_dong() {
        return da_dong;
    }

    public void setDa_dong(BigDecimal da_dong) {
        this.da_dong = da_dong;
    }

    public BigDecimal getCon_lai() {
        return con_lai;
    }

    public void setCon_lai(BigDecimal con_lai) {
        this.con_lai = con_lai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public Date getNgay_dong_cuoi() {
        return ngay_dong_cuoi;
    }

    public void setNgay_dong_cuoi(Date ngay_dong_cuoi) {
        this.ngay_dong_cuoi = ngay_dong_cuoi;
    }
    
    
}
