package com.example.ui_demo;

public class NhanVien {
    private int maso;
    private String hoTen;
    private String gioiTinh;
    private String donVi;

    public NhanVien(int maso, String hoTen, String gioiTinh, String donVi) {
        this.maso = maso;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.donVi = donVi;
    }

    public int getMaso() {
        return maso;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setMaso(int maso) {
        this.maso = maso;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    @Override
    public String toString() {
        return "NhanVien{"+ maso +", "+ hoTen + ", "+ gioiTinh +","+ donVi +"}";
    }
}
