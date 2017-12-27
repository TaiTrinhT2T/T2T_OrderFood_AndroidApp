package com.example.dell.dathangan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.dathangan.Model.Dish;
import com.example.dell.dathangan.R;
import com.example.dell.dathangan.Utils.BaseFragmentTransaction;
import com.example.dell.dathangan.Utils.BaseImagePreprocessing;

import java.util.List;

/**
 * Created by DELL on 21/11/2017.
 */

public class OrderAdapter extends BaseAdapter {

    private List<Dish> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public OrderAdapter(Context aContext,  List<Dish> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
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

    static class ViewHolder {
        int id;
        ImageView iconView;
        TextView nameDishView;
        TextView desDishView;
        TextView priceDishView;
        TextView proPriceDishView;
        Button btnLike;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_order, null);
            holder = new ViewHolder();
            holder.iconView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.nameDishView = (TextView) convertView.findViewById(R.id.name);
            holder.desDishView = (TextView) convertView.findViewById(R.id.description);
            holder.priceDishView = (TextView) convertView.findViewById(R.id.price);
            holder.proPriceDishView = (TextView) convertView.findViewById(R.id.promotionalPrice);
            holder.btnLike = (Button) convertView.findViewById(R.id.btn_detail_product);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Dish dish = this.listData.get(position);
        holder.nameDishView.setText(dish.TenMonAn);
        holder.desDishView.setText(dish.MoTa);
        holder.priceDishView.setText(dish.GiaBan);
        holder.proPriceDishView.setText(dish.GiaKhuyenMai);
        // Lấy ID của Ảnh theo tên lưu trong mipmap
        int imageId = BaseImagePreprocessing.getMipmapResIdByName(context, dish.SrcImg);
        holder.iconView.setImageResource(imageId);

        holder.btnLike.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //BaseFragmentTransaction.replace_FrContainer_To_FrDetailOrder(manager, dish);
            }
        });
        return convertView;
}
}
