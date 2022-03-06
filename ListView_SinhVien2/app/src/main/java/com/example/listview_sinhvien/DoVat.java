package com.example.listview_sinhvien;

public class DoVat {
    private int ID;
    private String Ten;
    private String MoTa;
    private byte[] Hinh;

    public DoVat(int ID, String ten, String moTa, byte[] hinh) {
        this.ID = ID;
        Ten = ten;
        MoTa = moTa;
        Hinh = hinh;
    }

    public int getID() {
        return ID;
    }

    public String getTen() {
        return Ten;
    }

    public String getMoTa() {
        return MoTa;
    }

    public byte[] getHinh() {
        return Hinh;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public void setHinh(byte[] hinh) {
        Hinh = hinh;
    }
}
