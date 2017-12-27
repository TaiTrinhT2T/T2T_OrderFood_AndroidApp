package com.example.dell.dathangan.Fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dell.dathangan.Adapter.CartAdapter;
import com.example.dell.dathangan.Model.Cart;
import com.example.dell.dathangan.R;
import com.example.dell.dathangan.Utils.BaseBundle;
import com.example.dell.dathangan.Utils.BaseFragmentTransaction;
import com.example.dell.dathangan.Utils.MySQLite;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestCartFragment extends Fragment {

    TextView txtThanhTien;
    TextView txtTongTien;
    TextView txtPhiDV;
    TextView tienHienCo;
    Button btnThanhToan;

    public TestCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_cart, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list_view_cart_test);
        txtThanhTien = (TextView) view.findViewById(R.id.txt_thanh_tien);
        txtTongTien = (TextView) view.findViewById(R.id.txt_tong_tien);
        txtPhiDV = (TextView) view.findViewById(R.id.txt_phi_dv);
        tienHienCo = (TextView) view.findViewById(R.id.txt_tien_hien_co);
        btnThanhToan = (Button) view.findViewById(R.id.btn_cart_thanh_toan);
        // TODO: Connect SQLite và đổ Data vào ListView
        final MySQLite db = new MySQLite(getContext());
        // db.deleteCart();
        ArrayList<Cart> carts = db.getAllCarts();

        // Đổ dữ liệu vào ListView
        if(!carts.isEmpty()) {
            // Đổ dữ liệu vào ListView
            listView.setAdapter(new CartAdapter(getContext(), carts, getFragmentManager()));
        }

        int i;
        int sum = 0;
        //Khởi tạo SharedPreferences có tên "MyShare"
        SharedPreferences share = this.getActivity().getSharedPreferences(BaseBundle.SHARE_PREFERRENCES_LOGIN, Context.MODE_PRIVATE);
        //Lấy chuỗi String trong file SharedPreferences thông qua tên
        String _money = share.getString(BaseBundle.SHARE_PREFERRENCES_LOGIN_MONEY, "");

        int phiDV = 1100;
        int len = carts.size();
        for( i = 0; i < len; i++){
            Cart item = carts.get(i);
            sum += (Integer.parseInt(item.getGiaKhuyenMai()) * item.getNumberItem());
        }
        int tongTien = sum + phiDV;
        txtThanhTien.setText("" + sum + " đ");
        txtTongTien.setText(""+tongTien + " đ");
        txtPhiDV.setText(""+phiDV + " đ");
        tienHienCo.setText(_money);

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                View mView = layoutInflater.inflate(R.layout.dialog_order_success, null);

                Button btnClose = (Button) mView.findViewById(R.id.btn_close_dialog_order_success);
                mBuilder.setView(mView);
                final AlertDialog alertDialog = mBuilder.create();
                alertDialog.show();
                btnClose.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                db.deleteCart();
                                TestCartFragment cartFragment = new TestCartFragment();
                                BaseFragmentTransaction.replaceFlagment(getFragmentManager(), cartFragment, R.id.fragment_container);
                                alertDialog.dismiss();
                            }
                        }
                );
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
