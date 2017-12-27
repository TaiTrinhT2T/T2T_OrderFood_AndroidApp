package com.example.dell.dathangan.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.dathangan.Fragment.HistoryFragment;
import com.example.dell.dathangan.Fragment.TestCartFragment;
import com.example.dell.dathangan.Model.Cart;
import com.example.dell.dathangan.Model.Dish;
import com.example.dell.dathangan.R;
import com.example.dell.dathangan.Utils.BaseFragmentTransaction;
import com.example.dell.dathangan.Utils.BaseImagePreprocessing;
import com.example.dell.dathangan.Utils.BaseViewHolder;
import com.example.dell.dathangan.Utils.MySQLite;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 28/11/2017.
 */

public class CartAdapter extends BaseAdapter {

    private ArrayList<Cart> listData;
    //private LayoutInflater layoutInflater;
    private Context context;
    private MySQLite db;
    android.support.v4.app.FragmentManager manager;

    /**
     * TODO: Hàm khởi tạo
     * @param aContext
     * @param listData Danh sách dữ liệu kiểu: Cast
     */
    public CartAdapter(Context aContext, ArrayList<Cart> listData, android.support.v4.app.FragmentManager manager) {
        this.context = aContext;
        this.listData = listData;
        db = new MySQLite(aContext);
        this.manager = manager;
        //layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gọi layoutInflater ra để bắt đầu ánh xạ view và data.
        LayoutInflater inflater = LayoutInflater.from(context);
        final BaseViewHolder.ViewHolder_Cart holder;
        if (convertView == null) {
            // Đổ dữ liệu vào biến View, view này chính là những gì nằm trong item_account.xml
            convertView = inflater.inflate(R.layout.item_cart, null);
            holder = new BaseViewHolder.ViewHolder_Cart();
            holder.imgCartIcon = (ImageView) convertView.findViewById(R.id.img_cart_imageView);
            holder.txtCartName = (TextView) convertView.findViewById(R.id.txt_cart_name);
            holder.txtCartDes = (TextView) convertView.findViewById(R.id.txt_cart_description);
            holder.txtCartPrice = (TextView) convertView.findViewById(R.id.txt_cart_price);
            holder.txtCartProPrice = (TextView) convertView.findViewById(R.id.txt_cart_promotionalPrice);
            holder.txtCartNumberItem = (TextView) convertView.findViewById(R.id.txt_number_item_cart);
            holder.btnCartDelete = (Button) convertView.findViewById(R.id.btn_delete_cart_item);
            holder.btnCartEdit = (Button) convertView.findViewById(R.id.btn_update_cart_item);
            convertView.setTag(holder);
        } else {
            holder = (BaseViewHolder.ViewHolder_Cart) convertView.getTag();
        }

        final Cart cart = this.listData.get(position);
        holder.txtCartName.setText(cart.getTenMonAn());
        holder.txtCartDes.setText(cart.getMoTa());
        holder.txtCartPrice.setText(cart.getGiaBan());
        holder.txtCartProPrice.setText(cart.getGiaKhuyenMai());
        holder.txtCartNumberItem.setText(""+cart.getNumberItem());
        // TODO: Lấy ID của Ảnh theo tên lưu trong mipmap
//        int imageId = BaseImagePreprocessing.getMipmapResIdByName(context, cart.getSrcImg());
//        holder.imgCartIcon.setImageResource(imageId);

        String urlImage = cart.getSrcImg();
        Picasso.with(context).load(urlImage).into(holder.imgCartIcon);

        holder.btnCartDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteNote(cart);
                TestCartFragment cartFragment = new TestCartFragment();
                BaseFragmentTransaction.replaceFlagment(manager, cartFragment, R.id.fragment_container);
            }
        });

        holder.btnCartEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateNote(cart);
                TestCartFragment cartFragment = new TestCartFragment();
                BaseFragmentTransaction.replaceFlagment(manager, cartFragment, R.id.fragment_container);

                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View mView = layoutInflater.inflate(R.layout.dialog_add_item_to_cart, null);

                ImageView imgItem = (ImageView) mView.findViewById(R.id.img_add_item_to_cart);
                TextView proPriceItemAddToCart = (TextView) mView.findViewById(R.id.pro_price_add_item_to_cart);
                TextView nameItemAddToCart = (TextView) mView.findViewById(R.id.name_add_item_to_cart);
                Button btnIncrease = (Button) mView.findViewById(R.id.btn_add_number_item_to_cart);
                Button btnDecrease = (Button) mView.findViewById(R.id.btn_sub_number_item_to_cart);
                Button btnClose = (Button) mView.findViewById(R.id.btn_close_item_to_cart);
                TextView _txtNumber = (TextView) mView.findViewById(R.id.txt_number_item_to_cart);

//                int imageId = BaseImagePreprocessing.getMipmapResIdByName(context, cart.getSrcImg());
//                imgItem.setImageResource(imageId);
                String urlImage = cart.getSrcImg();
                Picasso.with(context).load(urlImage).into(imgItem);

                nameItemAddToCart.setText(cart.getTenMonAn());
                proPriceItemAddToCart.setText(cart.getGiaKhuyenMai());
                _txtNumber.setText(cart.getNumberItem()+"");

                Button btnAddItemToCart = (Button) mView.findViewById(R.id.btn_add_item_to_cart);
                btnAddItemToCart.setText("Cập nhật");

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
                        if(number == 0){
                            db.deleteNote(cart);
                        } else {
                            Cart itemUpdate = new Cart(cart.getID_MonAn(),cart.getTenMonAn(),cart.getMoTa(), cart.getGiaBan(),cart.getGiaKhuyenMai(),cart.getSrcImg(), number);
                            db.updateNote(itemUpdate);
                        }
                        TestCartFragment cartFragment = new TestCartFragment();
                        BaseFragmentTransaction.replaceFlagment(manager, cartFragment, R.id.fragment_container);
                        alertDialog.dismiss();
                    }
                });
            }
        });

        return convertView;
    }
}
