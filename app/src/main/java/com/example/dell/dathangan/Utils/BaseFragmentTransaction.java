package com.example.dell.dathangan.Utils;

import android.os.Bundle;

import com.example.dell.dathangan.Fragment.AccountFragment;
import com.example.dell.dathangan.Fragment.DetailOrderFragment;
import com.example.dell.dathangan.Model.Dish;
import com.example.dell.dathangan.Fragment.OrderFragment;
import com.example.dell.dathangan.R;
import com.example.dell.dathangan.Fragment.SettingFragment;

/**
 * Created by DELL on 09/11/2017.
 * TODO: Hàm này xử lý: chuyển Fragment A ---> Fragment B (Giống chuyển màn hình giữa các Activity )
 */

public class BaseFragmentTransaction {
    // TODO: Chuyển từ Fragment có ID = frameId ---> fragment
    public static void replaceFlagment(android.support.v4.app.FragmentManager manager, android.support.v4.app.Fragment fragment, int frameId){
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                manager.beginTransaction();
        fragmentTransaction.replace(frameId, fragment);
        fragmentTransaction.commit();
    }

    // TODO: Chuyển từ Fragment Container ---> Flagment Order
    public static void replace_FrContainer_To_FrOrder(android.support.v4.app.FragmentManager manager){
        OrderFragment fragmentOrder = new OrderFragment();
        replaceFlagment(manager, fragmentOrder, R.id.fragment_container);
    }

    // TODO: Chuyển từ Fragment Container ---> Flagment Setting
    public static void replace_FrContainer_To_FrSetting(android.support.v4.app.FragmentManager manager){
        SettingFragment fragmentSetting = new SettingFragment();
        replaceFlagment(manager, fragmentSetting, R.id.fragment_container);
    }

    // TODO: Chuyển từ Fragment Container ---> Flagment Account
    public static void replace_FrContainer_To_FrAccount(android.support.v4.app.FragmentManager manager){
        AccountFragment fragmentAccount = new AccountFragment();
        replaceFlagment(manager, fragmentAccount, R.id.fragment_container);
    }

    /**
     * TODO: Khi click vào Button "Chi tiết" ở Fragment Order thì thực hiện Chuyển từ Fragment Container ---> Fragment Detail Order
     * @param manager
     * @param dish
     */
    public static void replace_FrContainer_To_FrDetailOrder(android.support.v4.app.FragmentManager manager, Dish dish){

        android.support.v4.app.FragmentTransaction ft = manager.beginTransaction();

        DetailOrderFragment llf = new DetailOrderFragment();
        // Truyen Parames tu Fragment nay sang Fragment khac
        Bundle args = new Bundle();
        args.putInt(BaseBundle.ID_ITEM_ORDER, dish.ID_MonAn);
        args.putString(BaseBundle.NAME_ITEM_ORDER, dish.TenMonAn);
        args.putString(BaseBundle.PRICE_ITEM_ORDER, dish.GiaBan);
        args.putString(BaseBundle.PRO_PRICE_ITEM_ORDER, dish.GiaKhuyenMai);
        args.putString(BaseBundle.DES_ITEM_ORDER, dish.MoTa);
        args.putString(BaseBundle.ICON_ITEM_ORDER, dish.SrcImg);
        llf.setArguments(args);

        ft.replace(R.id.fragment_container, llf);
        ft.commit();
    }
}
