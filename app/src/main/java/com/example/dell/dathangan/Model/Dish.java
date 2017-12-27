package com.example.dell.dathangan.Model;

/**
 * Created by DELL on 18/10/2017.
 * TODO: Lớp Dish --- 1.dùng trong Adapter: CustomListDishAdapter
 * TODO: Chú ý các trường phải giống với Data từ API trả về,
 * TODO: --- 2. để phục vụ cho hàm chuyển từ JSON --> List<T>
 */

public class Dish {
    public int ID_MonAn;
    public String TenMonAn;
    public String MoTa;
    public String GiaBan;
    public String GiaKhuyenMai;
    public String SrcImg;

    public Dish(int ID_MonAn, String TenMonAn, String MoTa, String GiaBan, String GiaKhuyenMai, String SrcImg){
        this.ID_MonAn = ID_MonAn;
        this.TenMonAn = TenMonAn;
        this.MoTa = MoTa;
        this.GiaBan = GiaBan;
        this.GiaKhuyenMai = GiaKhuyenMai;
        this.SrcImg = SrcImg;
    }
//
//    public int getId() {
//        return ID_MonAn;
//    }
//
//    public void setId(int id) {
//        this.ID_MonAn = id;
//    }
//
//    public String getName() {
//        return TenMonAn;
//    }
//
//    public void setName(String name) {
//        this.TenMonAn = name;
//    }
//
//    public String getDescription() {
//        return MoTa;
//    }
//
//    public void setDescription(String description) {
//        this.MoTa = description;
//    }
//
//    public String getPrice() {
//        return GiaBan + " đ";
//    }
//
//    public void setPrice(String price) {
//        this.GiaBan = price;
//    }
//
//    public String getPromotionalPrice() {
//        return GiaKhuyenMai + " đ";
//    }
//
//    public void setPromotionalPrice(String promotionalPrice) {
//        this.GiaKhuyenMai = promotionalPrice;
//    }
//
//    public String getSrcImg() {
//        return SrcImg;
//    }
//
//    public void setSrcImg(String srcImg) {
//        this.SrcImg = srcImg;
//    }
}
