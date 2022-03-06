package com.example.listview_sinhvien;

public class TraiCay {
    private String Ten;
    private String MoTa;
    private int Hinh;

    public TraiCay(String ten, String moTa, int hinh) {
        Ten = ten;
        MoTa = moTa;
        Hinh = hinh;
    }

    public String getTen() {
        return Ten;
    }

    public String getMoTa() {
        return MoTa;
    }

    public int getHinh() {
        return Hinh;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }
}
