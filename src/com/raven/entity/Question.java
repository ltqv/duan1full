package com.raven.entity;

public class Question {
    private String id;
    private int so_thu_tu;
    private String id_bai_kt;
    private String noi_dung;
    private String dap_an_a;
    private String dap_an_b;
    private String dap_an_c;
    private String dap_an_d;
    private String dap_an_dung;

    public Question() {
    }

    public Question(String id, int so_thu_tu, String id_bai_kt, String noi_dung, String dap_an_a, String dap_an_b, String dap_an_c, String dap_an_d, String dap_an_dung) {
        this.id = id;
        this.so_thu_tu = so_thu_tu;
        this.id_bai_kt = id_bai_kt;
        this.noi_dung = noi_dung;
        this.dap_an_a = dap_an_a;
        this.dap_an_b = dap_an_b;
        this.dap_an_c = dap_an_c;
        this.dap_an_d = dap_an_d;
        this.dap_an_dung = dap_an_dung;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSo_thu_tu() {
        return so_thu_tu;
    }

    public void setSo_thu_tu(int so_thu_tu) {
        this.so_thu_tu = so_thu_tu;
    }

    public String getId_bai_kt() {
        return id_bai_kt;
    }

    public void setId_bai_kt(String id_bai_kt) {
        this.id_bai_kt = id_bai_kt;
    }

    public String getNoi_dung() {
        return noi_dung;
    }

    public void setNoi_dung(String noi_dung) {
        this.noi_dung = noi_dung;
    }

    public String getDap_an_a() {
        return dap_an_a;
    }

    public void setDap_an_a(String dap_an_a) {
        this.dap_an_a = dap_an_a;
    }

    public String getDap_an_b() {
        return dap_an_b;
    }

    public void setDap_an_b(String dap_an_b) {
        this.dap_an_b = dap_an_b;
    }

    public String getDap_an_c() {
        return dap_an_c;
    }

    public void setDap_an_c(String dap_an_c) {
        this.dap_an_c = dap_an_c;
    }

    public String getDap_an_d() {
        return dap_an_d;
    }

    public void setDap_an_d(String dap_an_d) {
        this.dap_an_d = dap_an_d;
    }

    public String getDap_an_dung() {
        return dap_an_dung;
    }

    public void setDap_an_dung(String dap_an_dung) {
        this.dap_an_dung = dap_an_dung;
    }

    
}
