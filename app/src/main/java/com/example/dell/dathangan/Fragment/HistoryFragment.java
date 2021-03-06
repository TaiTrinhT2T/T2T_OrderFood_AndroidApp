package com.example.dell.dathangan.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dell.dathangan.R;
import com.example.dell.dathangan.Utils.BaseBundle;
import com.example.dell.dathangan.Utils.BaseHttp;

/**
 * A simple {@link Fragment} subclass.
 * TODO: Màn hình Lịch sử
 */
public class HistoryFragment extends Fragment {

    ListView listView;

    public HistoryFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        listView = (ListView) view.findViewById(R.id.lv_fm_history);
        // TODO: Connect API và đổ Data vào ListView
        SharedPreferences share = getActivity().getSharedPreferences(BaseBundle.SHARE_PREFERRENCES_LOGIN, getContext().MODE_PRIVATE);
        //Lấy chuỗi String trong file SharedPreferences thông qua tên
        String _id = share.getString(BaseBundle.SHARE_PREFERRENCES_LOGIN_ID, "");

        BaseHttp.FragmentHistory_GetAll(getContext(), listView, _id);
        return view;
    }

}
