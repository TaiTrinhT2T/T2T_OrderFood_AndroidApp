package com.example.dell.dathangan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.dathangan.Model.Account;
import com.example.dell.dathangan.Model.Cart;
import com.example.dell.dathangan.R;
import com.example.dell.dathangan.Utils.BaseImagePreprocessing;
import com.example.dell.dathangan.Utils.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by DELL on 29/11/2017.
 */

public class Cart2Adapter extends ArrayAdapter<Cart> {
    private Context context;
    private ArrayList<Cart> carts;

    /**
     * TODO: Hàm khởi tạo
     * @param context
     * @param carts
     */
    public Cart2Adapter(Context context, ArrayList<Cart> carts) {
        super(context,0, carts);
        //super(context, 0, carts);
        this.context = context;
        this.carts = carts;
    }

    /**
     * TODO: Lấy số lượng phần tử
     * @return
     */
    @Override
    public int getCount() {
        return carts.size();
    }

    /**
     * TODO: Lấy ra Item ở vị trí thứ position
     * @param position
     * @return
     */
    @Override
    public Cart getItem(int position) {
        return carts.get(position);
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
            convertView.setTag(holder);
        } else {
            holder = (BaseViewHolder.ViewHolder_Cart) convertView.getTag();
        }

        final Cart cart = this.carts.get(position);
        holder.txtCartName.setText(cart.getTenMonAn());
        holder.txtCartDes.setText(cart.getMoTa());
        holder.txtCartPrice.setText(cart.getGiaBan());
        holder.txtCartProPrice.setText(cart.getGiaKhuyenMai());
        holder.txtCartNumberItem.setText(""+cart.getNumberItem());
        // TODO: Lấy ID của Ảnh theo tên lưu trong mipmap
        int imageId = BaseImagePreprocessing.getMipmapResIdByName(context, cart.getSrcImg());
        holder.imgCartIcon.setImageResource(imageId);
        // Trả về view kết quả.
        return convertView;
    }
}
