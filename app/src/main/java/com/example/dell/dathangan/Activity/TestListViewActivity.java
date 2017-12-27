package com.example.dell.dathangan.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.dell.dathangan.Adapter.CartAdapter;
import com.example.dell.dathangan.Model.Cart;
import com.example.dell.dathangan.R;
import com.example.dell.dathangan.Utils.MySQLite;

import java.util.ArrayList;

public class TestListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list_view);
        ListView listView = (ListView) findViewById(R.id.lv_test);
        // TODO: Connect SQLite và đổ Data vào ListView
        //db = new MySQLite(getContext());
        // db.deleteCart();
        //ArrayList<Cart> carts = db.getAllCarts();
//
//        ArrayList<Cart> aaa = new ArrayList<Cart>();
//        Cart a1 = new Cart(1,"aaa","aaa","aaa","aaa","aaa",1);
//        Cart a2 = new Cart(2,"aaa","aaa","aaa","aaa","aaa",2);
//        Cart a3 = new Cart(3,"aaa","aaa","aaa","aaa","aaa",3);
//        aaa.add(a1);
//        aaa.add(a2);
//        aaa.add(a3);
//
//        // Đổ dữ liệu vào ListView
//        if(!aaa.isEmpty()) {
//            // Đổ dữ liệu vào ListView
//            listView.setAdapter(new CartAdapter(this, aaa));
//        }
    }
}
