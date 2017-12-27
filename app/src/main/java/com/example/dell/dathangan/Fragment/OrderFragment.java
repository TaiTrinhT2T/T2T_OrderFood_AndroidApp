package com.example.dell.dathangan.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dell.dathangan.R;
import com.example.dell.dathangan.Utils.BaseHttp;

/**
 * A simple {@link Fragment} subclass.
 * TODO: Màn hình Hiển thị ra các món ăn để đặt hàng
 */
public class OrderFragment extends Fragment {
    ListView listView;

    public OrderFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        listView = (ListView) view.findViewById(R.id.listView_order);
        // TODO: Connect API và đổ Data vào ListView
        BaseHttp.FragmentOrder_GetAllOrder(getContext(), listView, getFragmentManager());
        return view;
    }
}


