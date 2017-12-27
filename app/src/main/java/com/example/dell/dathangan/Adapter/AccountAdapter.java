package com.example.dell.dathangan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.dathangan.Model.Account;
import com.example.dell.dathangan.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 17/10/2017.
 * Đổ dữ liệu lên listItem: TODO: R.layout.item_account
 * Sau đó hiển thị các Item đó lên listView: TODO: R.id.listView_account
 * listView này được khai báo trong: TODO: AccountFragment
 */

public class AccountAdapter extends ArrayAdapter<Account> {
    private Context context;
    private ArrayList<Account> account;

    /**
     * TODO: Hàm khởi tạo
     * @param context
     * @param account
     */
    public AccountAdapter(Context context, ArrayList<Account> account) {
        super(context, 0, account);
        this.context = context;
        this.account = account;
    }

    /**
     * TODO: Lấy số lượng phần tử
     * @return
     */
    @Override
    public int getCount() {
        return account.size();
    }

    /**
     * TODO: Lấy ra Item ở vị trí thứ position
     * @param position
     * @return
     */
    @Override
    public Account getItem(int position) {
        return account.get(position);
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
     * @param view
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // Gọi layoutInflater ra để bắt đầu ánh xạ view và data.
        LayoutInflater inflater = LayoutInflater.from(context);

        // Đổ dữ liệu vào biến View, view này chính là những gì nằm trong item_account.xml
        view = inflater.inflate(R.layout.item_account, parent, false);

        // Đặt chữ cho từng view trong danh sách.
        TextView tvName = (TextView) view.findViewById(R.id.tv_login_name);
        TextView tvId = (TextView) view.findViewById(R.id.tv_id);
        ImageView ivAvatar = (ImageView) view.findViewById(R.id.iv_avatar);

        Account u = account.get(position);
        tvName.setText(u.login);
        tvId.setText(String.valueOf(u.id));
        // Sử dụng thư viện Piccaso để hỗ trợ load ảnh từ Url
        Picasso.with(context).load(u.avatar_url).into(ivAvatar);
        // Trả về view kết quả.
        return view;
    }
}
