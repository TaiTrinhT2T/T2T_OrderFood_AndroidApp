package com.example.dell.dathangan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.dathangan.Model.History;
import com.example.dell.dathangan.R;
import com.example.dell.dathangan.Utils.BaseViewHolder;

import java.util.List;

/**
 * Created by DELL on 12/12/2017.
 */

public class HistoryAdapter extends BaseAdapter {
    private Context context;
    private List<History> h;

    /**
     * TODO: Hàm khởi tạo
     * @param context
     * @param h
     */
    public HistoryAdapter(Context context, List<History> h) {
        this.context = context;
        this.h = h;
    }

    /**
     * TODO: Lấy số lượng phần tử
     * @return
     */
    @Override
    public int getCount() {
        return h.size();
    }

    /**
     * TODO: Lấy ra Item ở vị trí thứ position
     * @param position
     * @return
     */
    @Override
    public History getItem(int position) {
        return h.get(position);
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

        final BaseViewHolder.ViewHolder_History holder;
        if (convertView == null) {
            // Đổ dữ liệu vào biến View, view này chính là những gì nằm trong item_listview_history.xml
            convertView = inflater.inflate(R.layout.item_listview_history, parent, false);
            holder = new BaseViewHolder.ViewHolder_History();
            holder.txtHistory_MaDonHang = (TextView) convertView.findViewById(R.id.fr_history_ma_don_hang);
            holder.txtHistory_TenNhanHang = (TextView) convertView.findViewById(R.id.fr_history_ten_nha_hang);
            holder.txtHistory_TimeDatHang = (TextView) convertView.findViewById(R.id.fr_history_time_dat_hang);
            holder.txtHistory_TrangThai = (TextView) convertView.findViewById(R.id.fr_history_trang_thai);
            convertView.setTag(holder);
        } else {
            holder = (BaseViewHolder.ViewHolder_History) convertView.getTag();
        }

        History item = this.h.get(position);
        holder.txtHistory_MaDonHang.setText(item.getID_DonHang());
        holder.txtHistory_TenNhanHang.setText(item.getTenNhaHang());
        holder.txtHistory_TimeDatHang.setText(item.getThoiGian());
        holder.txtHistory_TrangThai.setText(item.getTrangThai());
        // Trả về view kết quả.
        return convertView;
    }
}
