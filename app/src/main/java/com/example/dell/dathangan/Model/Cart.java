package com.example.dell.dathangan.Model;

/**
 * Created by DELL on 27/11/2017.
 */

public class Cart {
    public int getID_MonAn() {
        return ID_MonAn;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public String getMoTa() {
        return MoTa;
    }

    public String getGiaBan() {
        return GiaBan;
    }

    public String getGiaKhuyenMai() {
        return GiaKhuyenMai;
    }

    public String getSrcImg() {
        return SrcImg;
    }

    public int getNumberItem() {
        return NumberItem;
    }

    public void setID_MonAn(int ID_MonAn) {
        this.ID_MonAn = ID_MonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        TenMonAn = tenMonAn;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public void setGiaBan(String giaBan) {
        GiaBan = giaBan;
    }

    public void setGiaKhuyenMai(String giaKhuyenMai) {
        GiaKhuyenMai = giaKhuyenMai;
    }

    public void setSrcImg(String srcImg) {
        SrcImg = srcImg;
    }

    public void setNumberItem(int numberItem) {
        NumberItem = numberItem;
    }

    private int ID_MonAn;
    private String TenMonAn;
    private String MoTa;
    private String GiaBan;
    private String GiaKhuyenMai;
    private String SrcImg;
    private int NumberItem;

    public Cart(){
    }

    public Cart(int ID_MonAn, String TenMonAn, String MoTa, String GiaBan, String GiaKhuyenMai, String SrcImg, int NumberItem){
        this.ID_MonAn = ID_MonAn;
        this.TenMonAn = TenMonAn;
        this.MoTa = MoTa;
        this.GiaBan = GiaBan;
        this.GiaKhuyenMai = GiaKhuyenMai;
        this.SrcImg = SrcImg;
        this.NumberItem = NumberItem;
    }

}
