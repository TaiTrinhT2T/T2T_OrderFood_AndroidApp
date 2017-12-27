package com.example.dell.dathangan.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.dell.dathangan.Fragment.AccountFragment;
import com.example.dell.dathangan.Fragment.HistoryFragment;
import com.example.dell.dathangan.Fragment.OrderFragment;
import com.example.dell.dathangan.Fragment.SettingFragment;
import com.example.dell.dathangan.Fragment.TestCartFragment;
import com.example.dell.dathangan.R;
import com.example.dell.dathangan.Utils.BaseBundle;
import com.example.dell.dathangan.Utils.BaseFragmentTransaction;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Khai báo biến navigationView và toolbar
     */
    NavigationView navigationView = null;
    Toolbar toolbar = null;
    private static final String TAG = "MainActivity";

    TabLayout tabLayout;

    private int[] tabIcons = {
            R.drawable.ic_order,
            R.drawable.ic_account,
            R.drawable.ic_settings,
            R.drawable.cart
    };

    //TODO: Khai báo các fragment
    OrderFragment fragmentOrder = new OrderFragment();
    SettingFragment fragmentSetting = new SettingFragment();
    AccountFragment fragmentAccount = new AccountFragment();
    TestCartFragment testCartFragment = new TestCartFragment();
    HistoryFragment historyFragment = new HistoryFragment();

    /**
     * Hàm onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Thêm Tab bar
        tabLayout = (TabLayout)findViewById(R.id.tabBar);
        tabLayout.addTab(tabLayout.newTab().setText(BaseBundle.TAB_ORDER));
        tabLayout.addTab(tabLayout.newTab().setText(BaseBundle.TAB_CART));
        tabLayout.addTab(tabLayout.newTab().setText(BaseBundle.TAB_HISTORY));
        // SetIcon
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[3]);
        tabLayout.getTabAt(2).setIcon(tabIcons[1]);
        // Event Click Tab bar
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText().toString().equals(BaseBundle.TAB_ORDER)){
                    BaseFragmentTransaction.replaceFlagment(getSupportFragmentManager(), fragmentOrder, R.id.fragment_container);
                } else if (tab.getText().toString().equals(BaseBundle.TAB_CART)){
                    BaseFragmentTransaction.replaceFlagment(getSupportFragmentManager(), testCartFragment, R.id.fragment_container);
                } else if (tab.getText().toString().equals(BaseBundle.TAB_HISTORY)){
                    BaseFragmentTransaction.replaceFlagment(getSupportFragmentManager(), historyFragment, R.id.fragment_container);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //Set the fragment initially
        BaseFragmentTransaction.replace_FrContainer_To_FrOrder(getSupportFragmentManager());

        //Khởi tạo SharedPreferences có tên "MyShare"
        SharedPreferences share = getSharedPreferences(BaseBundle.SHARE_PREFERRENCES_LOGIN, MODE_PRIVATE);
        //Lấy chuỗi String trong file SharedPreferences thông qua tên
        String _name = share.getString(BaseBundle.SHARE_PREFERRENCES_LOGIN_NAME, "");
        String _money = share.getString(BaseBundle.SHARE_PREFERRENCES_LOGIN_MONEY, "");
        String _id = share.getString(BaseBundle.SHARE_PREFERRENCES_LOGIN_ID, "");

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header=navigationView.getHeaderView(0);
        TextView userName = (TextView)header.findViewById(R.id.userName);
        userName.setText(_name);
        TextView idTaiKhoan = (TextView)header.findViewById(R.id.idUser);
        idTaiKhoan.setText(_id);
        TextView money = (TextView)header.findViewById(R.id.taiKhoan);
        money.setText(_money);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_order) {
            //TODO: Chuyển từ Fragment từ Fragment Container ---> Flagment Order
            BaseFragmentTransaction.replace_FrContainer_To_FrOrder(getSupportFragmentManager());
        } else if (id == R.id.nav_account) {
            // TODO: Chuyển từ Fragment Container ---> Flagment Account
            BaseFragmentTransaction.replaceFlagment(getSupportFragmentManager(), fragmentAccount, R.id.fragment_container);
        } else if (id == R.id.nav_setting) {
            // TODO: Chuyển từ Fragment Container ---> Flagment Setting
            BaseFragmentTransaction.replaceFlagment(getSupportFragmentManager(), fragmentSetting, R.id.fragment_container);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
