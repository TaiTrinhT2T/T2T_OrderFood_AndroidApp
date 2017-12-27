package com.example.dell.dathangan.Utils;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by DELL on 09/11/2017.
 * TODO: Class này lưu tất cả các ViewHoder được sử dụng trong Adapter
 */

public class BaseViewHolder {
    /**
     * TODO: Class này khai báo các view có ID trong Layout : R.layout.item_order
     */
    public static class ViewHolder_Dish {
        public ImageView iconView;
        public TextView nameDishView;
        public TextView desDishView;
        public TextView priceDishView;
        public TextView proPriceDishView;
        public TextView txtOrderProductNumber;
        public Button btnLikeProduct;
        public Button btnAddProcduct;
        public Button btnDetailProduct;
        public Button btnSubProduct;
    }

    /**
     * TODO: Class này khai báo các view có ID trong Layout : R.layout.item_cart
     */
    public static class ViewHolder_Cart {
        public ImageView imgCartIcon;
        public TextView txtCartName;
        public TextView txtCartDes;
        public TextView txtCartPrice;
        public TextView txtCartProPrice;
        public TextView txtCartNumberItem;
        public Button btnCartDelete;
        public Button btnCartEdit;
    }

    /**
     * TODO: Class này khai báo các view có ID trong Layout : R.layout.item_listview_history
     */
    public static class ViewHolder_History {
        public TextView txtHistory_MaDonHang;
        public TextView txtHistory_TrangThai;
        public TextView txtHistory_TenNhanHang;
        public TextView txtHistory_TimeDatHang;
    }
}
