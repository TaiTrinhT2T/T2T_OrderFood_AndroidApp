package com.example.dell.dathangan.Fragment;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dell.dathangan.Model.Account;
import com.example.dell.dathangan.R;
import com.example.dell.dathangan.Utils.BaseBundle;
import com.example.dell.dathangan.Utils.BaseHttp;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * TODO: Màn hình Tài khoản
 */
public class AccountFragment extends Fragment {
    private TextView txtName;
    private TextView txtMoney;
    private TextView txtEmail;
    private TextView txtPhone;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        //Khởi tạo SharedPreferences có tên "MyShare"
        SharedPreferences share = getActivity().getSharedPreferences(BaseBundle.SHARE_PREFERRENCES_LOGIN, getContext().MODE_PRIVATE);
        //Lấy chuỗi String trong file SharedPreferences thông qua tên
        String _name = share.getString(BaseBundle.SHARE_PREFERRENCES_LOGIN_NAME, "");
        String _money = share.getString(BaseBundle.SHARE_PREFERRENCES_LOGIN_MONEY, "");
        String _phone = share.getString(BaseBundle.SHARE_PREFERRENCES_LOGIN_PHONE, "");
        String _email = share.getString(BaseBundle.SHARE_PREFERRENCES_LOGIN_EMAIL, "");

        txtName = (TextView) view.findViewById(R.id.fr_account_name);
        txtEmail = (TextView) view.findViewById(R.id.fr_account_email);
        txtMoney = (TextView) view.findViewById(R.id.fr_account_money);
        txtPhone = (TextView) view.findViewById(R.id.fr_account_phone);

        txtMoney.setText(_money);
        txtEmail.setText(_email);
        txtPhone.setText(_phone);
        txtName.setText(_name);

        return view;
    }

}
