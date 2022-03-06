package com.example.quanlynhanvien;

public class NhanVien {
    private int Id;
    private String maso;
    private String hoten;
    private String gioitinh;
    private String donvi;
    private byte[] hinh;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMaso() {
        return maso;
    }

    public void setMaso(String maso) {
        this.maso = maso;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public NhanVien(int id, String maso, String hoten, String gioitinh, String donvi, byte[] hinh) {
        Id = id;
        this.maso = maso;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.donvi = donvi;
        this.hinh = hinh;
    }
}
