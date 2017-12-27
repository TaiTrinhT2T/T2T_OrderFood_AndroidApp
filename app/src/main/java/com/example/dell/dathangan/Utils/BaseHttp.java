package com.example.dell.dathangan.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.dathangan.Activity.LoginActivity;
import com.example.dell.dathangan.Adapter.AccountAdapter;
import com.example.dell.dathangan.Adapter.CustomListDishAdapter;
import com.example.dell.dathangan.Activity.MainActivity;
import com.example.dell.dathangan.Adapter.HistoryAdapter;
import com.example.dell.dathangan.Model.Account;
import com.example.dell.dathangan.Model.Dish;
import com.example.dell.dathangan.Model.History;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by DELL on 09/11/2017.
 * Lớp này dùng để lưu tất cả các kêt nối : Get, Post, Put, Delete của Fragment, Activity
 */

public class BaseHttp {
    private static ProgressDialog progressDialog;
    private static Result resultLogin = new Result();
    private static Result resultSignUp = new Result();
    // Khởi tạo OkHttpClient để lấy dữ liệu.
    private static OkHttpClient client = new OkHttpClient();

    public static final MediaType MEDIA_TYPE = MediaType.parse("application/json");
    /*
      TODO: Cách đặt tên hàm trong Class BaseHttp:
      Nếu là Fragment  : Fragment+TênFragment_PhuongThuc(Get, Post, Put, Delete)
      Nếu là Activity : Activity+TenActivity_PhuongThuc(Get, Post, Put, Delete)
     */

    /**
     * connectHttp
     * TODO: connect http sau đó đổ dữ liệu lên view
     */
    public static void connectHttp(
            final Context context,
            final ListView listView,
            String url,
            final String className){
        // Khởi tạo Moshi adapter để biến đổi json sang model java (ở đây là User)
        Moshi moshi = new Moshi.Builder().build();
        // TODO: Bước 1: Khai báo các JsonAdapter
        JsonAdapter<ArrayList<Account>> jsonAdapterAccount = null;
        JsonAdapter<ArrayList<Dish>> jsonAdapterDish = null;
        JsonAdapter<ArrayList<History>> jsonAdapterHistory = null;
        //TODO: Bước 2: Thêm case mới khi có 1 Model mới; Khởi tạo các JsonAdapter tương ứng theo className truyền vào
        switch (className){
            case BaseBundle.MODEL_ACCOUNT:
                Type usersType = Types.newParameterizedType(List.class, Account.class);
                jsonAdapterAccount = moshi.adapter(usersType);
                break;
            case BaseBundle.MODEL_DISH:
                Type type = Types.newParameterizedType(List.class, Dish.class);
                jsonAdapterDish = moshi.adapter(type);
                break;
            case BaseBundle.MODEL_HISTORY:
                Type historyType = Types.newParameterizedType(List.class, History.class);
                jsonAdapterHistory = moshi.adapter(historyType);
                break;
        }

        // Tạo request lên server.
        Request request = new Request.Builder()
                .url(url)
                .build();

        progressDialog = new ProgressDialog(context);
        //set message of the dialog
        progressDialog.setMessage("Loading...");
        //show dialog
        progressDialog.show();

        // TODO: Bước 3 : Khởi tạo thành final để có thể dùng trong hàm khác
        final JsonAdapter<ArrayList<Account>> finalJsonAdapterAccount = jsonAdapterAccount;
        final JsonAdapter<ArrayList<Dish>> finalJsonAdapterDish = jsonAdapterDish;
        final JsonAdapter<ArrayList<History>> finalJsonAdapterHistory = jsonAdapterHistory;
        // Thực thi request.
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                // Lấy thông tin JSON trả về.
                final String json = response.body().string();

                // TODO: Bước 4: Khởi tạo ArrayList<T>
                ArrayList<Account> arrAccountTemp = null;
                ArrayList<Dish> arrDishTemp = null;
                ArrayList<History> arrHistoryTemp = null;
                // TODO: Bước 5: Thêm case
                switch (className){
                    case BaseBundle.MODEL_ACCOUNT:
                        arrAccountTemp = finalJsonAdapterAccount.fromJson(json);
                        break;
                    case BaseBundle.MODEL_DISH:
                        arrDishTemp = finalJsonAdapterDish.fromJson(json);
                        break;
                    case BaseBundle.MODEL_HISTORY:
                        arrHistoryTemp = finalJsonAdapterHistory.fromJson(json);
                        break;
                }
                // TODO: Bước 6: Khởi tạo lại thành final
                final ArrayList<Account> arrAccount = arrAccountTemp;
                final ArrayList<Dish> arrDish = arrDishTemp;
                final ArrayList<History> arrHistory = arrHistoryTemp;
                // Cho hiển thị lên RecyclerView.
                BaseHttp.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO: Bước 7: Thêm case
                        switch (className){
                            case BaseBundle.MODEL_ACCOUNT:
                                // Đổ dữ liệu vào ListView
                                ArrayAdapter<Account> arrAdapterAccount = new AccountAdapter(context , arrAccount);
                                listView.setAdapter(arrAdapterAccount);
                                break;
                            case BaseBundle.MODEL_DISH:
                                // Đổ dữ liệu vào ListView
//                                ArrayAdapter<Dish> arrAdapterDish = new CustomListDishAdapter(context , arrDish);
//                                listView.setAdapter(arrAdapterDish);
                                break;
                            case BaseBundle.MODEL_HISTORY:
                                // Đổ dữ liệu vào ListView
                                listView.setAdapter(new HistoryAdapter(context, arrHistory));
                                break;
                        }
                        //hide the dialog
                        progressDialog.dismiss();
                    }
                });

            }
        });
    }

    // -------- TODO: Start - Conect API - Login Activity -------- //
    /**
     * ActivityLogin_CheckLoginAccount_GET
     * TODO: Kiểm tra xem đăng nhập có hợp lệ hay không
     */
    public static int ActivityLogin_CheckLoginAccount(final Context ctActivity_Start,final String email, final String password){
        // Result result = new Result();
        // Get URL All Users
        String urlCheck = BaseAPI.API_LOGIN_CHECK_ACCOUNT;
        // Thêm parame
        HttpUrl.Builder urlBuilder = HttpUrl.parse(urlCheck).newBuilder();
        urlBuilder.addQueryParameter("taiKhoan", email);
        urlBuilder.addQueryParameter("pass", password);
        String url = urlBuilder.build().toString();
        // Tạo request lên server.
        Request request = new Request.Builder()
                .url(url)
                .build();
        progressDialog = new ProgressDialog(ctActivity_Start);
        //set message of the dialog
        progressDialog.setMessage("Loading...");
        //show dialog
        progressDialog.show();
        // Thực thi request.
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                // Lấy thông tin JSON trả về.
                final String json = response.body().string();

                // Cho hiển thị lên RecyclerView.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        if( json.equals("true")){
                            resultLogin.setResult(1);
                        }
                        else{
                            resultLogin.setResult(0);
                        }
                    }
                });
            }
        });
        return resultLogin.getResult();
    }

    /**
     * ActivityLogin_CheckLoginAccount_POST
     * TODO: Kiểm tra xem đăng nhập có hợp lệ hay không
     */
    public static int ActivityLogin_CheckLoginAccount_Post(final Context ctActivity_Start,final String email, final String password){
        // Get URL All Users
        String urlCheck = BaseAPI.API_LOGIN_CHECK_ACCOUNT_POST;

        JSONObject formBody = new JSONObject();
        try {
            formBody.put("Name", email);
            formBody.put("Password", password);
//            formBody.put("pass", password);
        } catch(JSONException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestBody bodyaaa = RequestBody.create(MEDIA_TYPE, formBody.toString());

        Request request = new Request.Builder()
                .url(urlCheck)
                .post(bodyaaa)
                .addHeader("Content-Type", "application/json")
                .build();

        progressDialog = new ProgressDialog(ctActivity_Start);
        //set message of the dialog
        progressDialog.setMessage("Loading...");
        //show dialog
        progressDialog.show();
        // Thực thi request.
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                // Lấy thông tin JSON trả về.
                final String json = response.body().string();
//                Log.e("--------JSON----------", json);
//                Log.e("--------Response-------", String.valueOf(response));

                // Cho hiển thị lên RecyclerView.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        if( json.equals("true")){
                            resultLogin.setResult(1);
                        }
                        else{
                            resultLogin.setResult(0);
                        }
                    }
                });
            }
        });
        return resultLogin.getResult();
    }


    /**
     * ActivityLogin_SignUp
     * TODO: Đăng ký tài khoản mới
     */
    public static int ActivitySignUp_SignUp(
            final Context ctActivity_Start,
            final String tenTaiKhoan,
            final String email,
            final String phone,
            final String matKhau){
        // Get URL All Users
        String urlCheck = BaseAPI.API_SIGN_UP_ACCOUNT;
        // Thêm parame
        HttpUrl.Builder urlBuilder = HttpUrl.parse(urlCheck).newBuilder();
        urlBuilder.addQueryParameter("tenTaiKhoan", tenTaiKhoan);
        urlBuilder.addQueryParameter("email", email);
        urlBuilder.addQueryParameter("soDienThoai", phone);
        urlBuilder.addQueryParameter("matKhau", matKhau);
        String url = urlBuilder.build().toString();
        // Tạo request lên server.
        Request request = new Request.Builder()
                .url(url)
                .build();
        progressDialog = new ProgressDialog(ctActivity_Start);
        //set message of the dialog
        progressDialog.setMessage("Loading...");
        //show dialog
        progressDialog.show();
        // Thực thi request.
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                // Lấy thông tin JSON trả về.
                final String json = response.body().string();

                // Cho hiển thị lên RecyclerView.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        if( json.equals("true")){
                            resultSignUp.setResult(1);
                        }
                        else{
                            resultSignUp.setResult(0);
                        }
                    }
                });
            }
        });

        return resultSignUp.getResult();
    }
    // -------- End - Conect API - Login Activity -------- //

    // -------- TODO: Start - Conect API - Account Fragment -------- //
    /**
     * FragmentAccount_GetAllUsers
     * TODO: Lấy danh sách account
     */
    public static void FragmentAccount_GetAllUsers(final Context context, final ListView listView){
        // Get URL All Users
        String url = BaseAPI.API_GET_ALL_USERS;
        connectHttp(context, listView, url, BaseBundle.MODEL_ACCOUNT);
    }
    // -------- End - Conect API - Account Fragment -------- //

    // -------- TODO: Start - Conect API - Account Fragment -------- //
    /**
     * FragmentAccount_GetAllUsers
     * TODO: Lấy danh sách account
     */
    public static void FragmentHistory_GetAll(final Context context, final ListView listView,String idAccount){
        // Get URL All Users
        String urlHistory = BaseAPI.API_HISTORY_GET_ALL;
        HttpUrl.Builder urlBuilder = HttpUrl.parse(urlHistory).newBuilder();

        urlBuilder.addQueryParameter("idAccount", idAccount);
        String url = urlBuilder.build().toString();
        connectHttp(context, listView, url, BaseBundle.MODEL_HISTORY);
    }
    // -------- End - Conect API - Account Fragment -------- //

    // -------- TODO: Start - Conect API - Order Fragment -------- //
    /**
     * FragmentOrder_GetAllOrder
     * TODO: Lấy danh sách tất cả các món ăn
     */
    public static void FragmentOrder_GetAllOrder(final Context context, final ListView listView, final android.support.v4.app.FragmentManager manager) {
        // Get URL All Users
        String url = BaseAPI.API_GET_ALL_ORDERS;

        // Khởi tạo Moshi adapter để biến đổi json sang model java
        Moshi moshi = new Moshi.Builder().build();
        //
        Type type = Types.newParameterizedType(List.class, Dish.class);
        final JsonAdapter<ArrayList<Dish>> jsonAdapter = moshi.adapter(type);

        // Tạo request lên server.
        Request request = new Request.Builder()
                .url(url)
                .build();

        progressDialog = new ProgressDialog(context);
        //set message of the dialog
        progressDialog.setMessage("Loading...");
        //show dialog
        progressDialog.show();

        // Thực thi request.
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                // Lấy thông tin JSON trả về.
                final String json = response.body().string();
                final ArrayList<Dish> dishes = jsonAdapter.fromJson(json);
                // Cho hiển thị lên RecyclerView.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Đổ dữ liệu vào ListView
                        listView.setAdapter(new CustomListDishAdapter(context, dishes, manager));
                        progressDialog.dismiss();
                    }
                });

            }
        });
    }
    // -------- End - Conect API - Order Fragment -------- //

    //------------------------------- Start - Thread -----------------------------------------//
    private static Thread mUiThread = Thread.currentThread();
    final static Handler mHandler = new Handler();
    private static void runOnUiThread(Runnable action) {
        if (Thread.currentThread() != mUiThread) {
            mHandler.post(action);
        } else {
            action.run();
        }
    }
    //------------------------------- End - Thread -----------------------------------------//

    public static class Result{
        private int reSult;
        public int getResult() {
            return reSult;
        }

        public void setResult(int id) {
            this.reSult = id;
        }
    }

}
