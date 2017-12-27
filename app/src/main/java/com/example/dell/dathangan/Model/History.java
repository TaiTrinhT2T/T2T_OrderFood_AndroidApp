package com.example.dell.dathangan.Model;

/**
 * Created by DELL on 12/12/2017.
 */

public class History {
    private int ID_DonHang;
    private String TenNhaHang;
    private String DiaChi;
    private String SoDienThoai;
    private String ThoiGian;
    private int TrangThai;

    public String getID_DonHang() {
        return ID_DonHang+"";
    }

    public void setID_DonHang(int ID_DonHang) {
        this.ID_DonHang = ID_DonHang;
    }

    public String getTenNhaHang() {
        return TenNhaHang;
    }

    public void setTenNhaHang(String tenNhaHang) {
        TenNhaHang = tenNhaHang;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(String thoiGian) {
        ThoiGian = thoiGian;
    }

    public String getTrangThai() {
        if(TrangThai == 0){
            return "Completed";
        } else if (TrangThai == 1){
            return "Waiting";
        } else {
            return "Cancelled";
        }
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }
}
