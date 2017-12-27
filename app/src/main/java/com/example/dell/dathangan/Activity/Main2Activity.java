package com.example.dell.dathangan.Activity;

import android.os.BaseBundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.dell.dathangan.R;
import com.example.dell.dathangan.Utils.BaseFragmentTransaction;

public class Main2Activity extends AppCompatActivity {

    TabLayout tabLayout;

    private int[] tabIcons = {
            R.drawable.ic_order,
            R.drawable.ic_account,
            R.drawable.ic_settings,
            R.drawable.cart
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tabLayout = (TabLayout)findViewById(R.id.tabBarTest);
        final ImageView img = (ImageView) findViewById(R.id.imgViewTabLayout);
        img.setImageResource(R.drawable.ic_order);

        tabLayout.addTab(tabLayout.newTab().setText("A"));
        tabLayout.addTab(tabLayout.newTab().setText("B"));
        tabLayout.addTab(tabLayout.newTab().setText("C"));
        tabLayout.addTab(tabLayout.newTab().setText("D"));
        // SetIcon
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText().toString().equals("A")){
                    img.setImageResource(R.drawable.ic_order);
                } else if (tab.getText().toString().equals("B")){
                    img.setImageResource(R.drawable.ic_account);
                } else if (tab.getText().toString().equals("C")){
                    img.setImageResource(R.drawable.ic_settings);
                } else if (tab.getText().toString().equals("D")){
                    img.setImageResource(R.drawable.cart);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
