package com.example.dell.dathangan.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.dathangan.Model.Cart;
import com.example.dell.dathangan.Model.Dish;
import com.example.dell.dathangan.R;
import com.example.dell.dathangan.Utils.BaseFragmentTransaction;
import com.example.dell.dathangan.Utils.BaseImagePreprocessing;
import com.example.dell.dathangan.Utils.BaseViewHolder;
import com.example.dell.dathangan.Utils.MySQLite;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by DELL on 18/10/2017.
 * Đổ dữ liệu lên listItem: TODO: R.layout.item_order
 * Sau đó hiển thị các Item đó lên listView: TODO: R.id.listView_order
 * listView này được khai báo trong: TODO: OrderFragment
 */

public class CustomListDishAdapter extends BaseAdapter {

    private List<Dish> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    android.support.v4.app.FragmentManager manager;
    private MySQLite db;

    /**
     * TODO: Hàm khởi tạo
     * @param aContext
     * @param listData Danh sách dữ liệu kiểu: Dish
     * @param manager Tham số này lưu fragment Parent là gì, phục vụ cho FragmentTransaction
     */
    public CustomListDishAdapter(Context aContext,  List<Dish> listData, android.support.v4.app.FragmentManager manager) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
        this.manager = manager;
        // SQLite database handler
        db = new MySQLite(aContext);
    }

    /**
     * TODO: Lấy số lượng phần tử
     * @return
     */
    @Override
    public int getCount() {
        return listData.size();
    }

    /**
     * TODO: Lấy ra Item ở vị trí thứ position
     * @param position
     * @return
     */

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    /**
     * TODO: Lấy ra ItemId
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * TODO: Hiển thị lên view
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final BaseViewHolder.ViewHolder_Dish holder;
        if (convertView == null) {
//            convertView = layoutInflater.inflate(R.layout.item_order, null);
//            holder = new BaseViewHolder.ViewHolder_Dish();
//            holder.iconView = (ImageView) convertView.findViewById(R.id.imageView);
//            holder.nameDishView = (TextView) convertView.findViewById(R.id.name);
//            holder.desDishView = (TextView) convertView.findViewById(R.id.description);
//            holder.priceDishView = (TextView) convertView.findViewById(R.id.price);
//            holder.proPriceDishView = (TextView) convertView.findViewById(R.id.promotionalPrice);
//            holder.btnLikeProduct = (Button) convertView.findViewById(R.id.btn_like_product);
//            holder.btnDetailProduct = (Button) convertView.findViewById(R.id.btn_detail_product);
//            holder.btnAddProcduct = (Button) convertView.findViewById(R.id.btn_add_product);

//            convertView = layoutInflater.inflate(R.layout.item_order_template_2, null);
//            holder = new BaseViewHolder.ViewHolder_Dish();
//            holder.iconView = (ImageView) convertView.findViewById(R.id.imageView2);
//            holder.nameDishView = (TextView) convertView.findViewById(R.id.name2);
//            holder.desDishView = (TextView) convertView.findViewById(R.id.description2);
//            holder.priceDishView = (TextView) convertView.findViewById(R.id.price2);
//            holder.proPriceDishView = (TextView) convertView.findViewById(R.id.promotionalPrice2);
//            holder.btnDetailProduct = (Button) convertView.findViewById(R.id.btn_detail_product2);
//            holder.btnAddProcduct = (Button) convertView.findViewById(R.id.btn_add_product2);

            convertView = layoutInflater.inflate(R.layout.item_order_template_3, null);
            holder = new BaseViewHolder.ViewHolder_Dish();
            holder.iconView = (ImageView) convertView.findViewById(R.id.imageView3);
            holder.nameDishView = (TextView) convertView.findViewById(R.id.name3);
            //holder.desDishView = (TextView) convertView.findViewById(R.id.description2);
            //holder.priceDishView = (TextView) convertView.findViewById(R.id.price);
            holder.proPriceDishView = (TextView) convertView.findViewById(R.id.promotionalPrice3);
            holder.btnDetailProduct = (Button) convertView.findViewById(R.id.btn_detail_product3);
            holder.btnAddProcduct = (Button) convertView.findViewById(R.id.btn_add_product3);
            convertView.setTag(holder);
        } else {
            holder = (BaseViewHolder.ViewHolder_Dish) convertView.getTag();
        }

        final Dish dish = this.listData.get(position);
        holder.nameDishView.setText(dish.TenMonAn);
//        holder.desDishView.setText(dish.MoTa);
//        holder.priceDishView.setText(dish.GiaBan);
        holder.proPriceDishView.setText(dish.GiaKhuyenMai);
        // TODO: Lấy ID của Ảnh theo tên lưu trong mipmap
//        int imageId = BaseImagePreprocessing.getMipmapResIdByName(context, dish.SrcImg);
//        holder.iconView.setImageResource(imageId);

//        String urlImage = "https://lh3.googleusercontent.com/kKY75LsjUAeuT1v_qIe42tJ0okz7fexO2lHG_kAyvaNSX3ZsroVwtMrlYJc2c2X518sq0HN6Q8k7B_Sb0Im7UQlxAM1tUiTmqe724m16Lj3onN54KmT4sbGg8C2w6THK8vjIQFTAcRGiAXp-yeFKRCy6GZmSLBwthgMC4F0vUessGZ8f7SMnY0GR_J5p9xLR6DWTuAfCTXOcUTHfdynlx9kKrpqsE5LF123pnIUqbPCT9tnDXuRyVTHaUq0yJeyK9IeFWhBVllTccbm_ZsLb1FJFwgptBTg-8J_ZNuKhEcXAQYz3qF9zE3mHqCV4wAQpamgCeKRosAZHCEH2HWzPwjvJgTxbTnUBTMjv-W0OxuOBoN4EkTN2jwMoq2sn791k5xlv7_uHAm4u5jsEwjUmafFHNTymNa1wFXtd3_0u3rCRtKEDP31q_43ISfbY24hozI8Qn3vpZaXNpiDbCvkB1faxGbF4ANDdsZ8KpJ8ztexeVPLMo794QAbQi1bGRmM1Pi0-1tolzaUoDuhmdwhJWng10e0akWxwBcThcuzP57RhXJlaBrzDbi4hCX3kWsZ8oMXImufEewsgQBIBZUJvrfKiMe3OMgg=s450-no";
        // Sử dụng thư viện Piccaso để hỗ trợ load ảnh từ Url
        String urlImage = dish.SrcImg;
        Picasso.with(context).load(urlImage).into(holder.iconView);

        holder.btnDetailProduct.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                BaseFragmentTransaction.replace_FrContainer_To_FrDetailOrder(manager, dish);
            }
        });

        holder.btnAddProcduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                View mView = layoutInflater.inflate(R.layout.dialog_add_item_to_cart, null);

                ImageView imgItem = (ImageView) mView.findViewById(R.id.img_add_item_to_cart);
                TextView proPriceItemAddToCart = (TextView) mView.findViewById(R.id.pro_price_add_item_to_cart);
                TextView nameItemAddToCart = (TextView) mView.findViewById(R.id.name_add_item_to_cart);
                Button btnIncrease = (Button) mView.findViewById(R.id.btn_add_number_item_to_cart);
                Button btnDecrease = (Button) mView.findViewById(R.id.btn_sub_number_item_to_cart);
                Button btnClose = (Button) mView.findViewById(R.id.btn_close_item_to_cart);

//                int imageId = BaseImagePreprocessing.getMipmapResIdByName(context, dish.SrcImg);
//                imgItem.setImageResource(imageId);
                String urlImage = dish.SrcImg;
                Picasso.with(context).load(urlImage).into(imgItem);
                nameItemAddToCart.setText(dish.TenMonAn);
                proPriceItemAddToCart.setText(dish.GiaKhuyenMai);

                Button btnAddItemToCart = (Button) mView.findViewById(R.id.btn_add_item_to_cart);

                btnIncrease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView txtNumber = (TextView) v.getRootView().findViewById(R.id.txt_number_item_to_cart);
                        String strNumber = (String) txtNumber.getText();
                        //String strNumber = (String) holder.txtOrderProductNumber.getText();
                        int a = Integer.parseInt(strNumber);
                        a++;
                        txtNumber.setText(""+a);
                    }
                });

                btnDecrease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView txtNumber = (TextView) v.getRootView().findViewById(R.id.txt_number_item_to_cart);
                        String strNumber = (String) txtNumber.getText();
                        //String strNumber = (String) holder.txtOrderProductNumber.getText();
                        int a = Integer.parseInt(strNumber);
                        if(a > 0){
                            a--;
                        }
                        txtNumber.setText(""+a);
                    }
                });

                mBuilder.setView(mView);
                final AlertDialog alertDialog = mBuilder.create();
                alertDialog.show();

                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });


                btnAddItemToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Bắt đầu connect Http
                        // Inserting row in users table
                        TextView txtNumber = (TextView) v.getRootView().findViewById(R.id.txt_number_item_to_cart);
                        int number = Integer.parseInt((String) txtNumber.getText());
                        Cart a = new Cart(dish.ID_MonAn,dish.TenMonAn,dish.MoTa,dish.GiaBan,dish.GiaKhuyenMai,dish.SrcImg,number);
                        Cart getOldItem = db.getDataFromId(dish.ID_MonAn);
                        if(getOldItem.getID_MonAn() != -1){
                            //Cart getOldItem = db.getDataFromId(dish.ID_MonAn);
                            int numberUpdate = getOldItem.getNumberItem() + number;
                            Cart itemUpdate = new Cart(dish.ID_MonAn,dish.TenMonAn,dish.MoTa,dish.GiaBan,dish.GiaKhuyenMai,dish.SrcImg, numberUpdate);
                            db.updateNote(itemUpdate);
                        } else {
                            db.addItemToCart(a);
                        }

//                        }
                        alertDialog.dismiss();
                    }
                });
            }
        });
        return convertView;
    }
}
