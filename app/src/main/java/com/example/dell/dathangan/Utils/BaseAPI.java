package com.example.dell.dathangan.Utils;

/**
 * Created by DELL on 09/11/2017.
 * TODO: Lớp này dùng để lưu tất cả các API sẽ dùng trong App
 */

public class BaseAPI {
//    static String ipAddress = "http://192.168.154.2";
    static String ipAddress = "http://datmonancloud.azurewebsites.net";
    public static final String API_GET_ALL_USERS = "https://api.github.com/users";
    public static final String API_GET_ALL_ORDERS = ipAddress+"/api/getAllMonAn";
    public static final String API_LOGIN_CHECK_ACCOUNT = ipAddress+"/api/Login";
    public static final String API_LOGIN_CHECK_ACCOUNT_POST = ipAddress+"/api/LoginPost";
    public static final String API_SIGN_UP_ACCOUNT = ipAddress+"/api/AccountInsert";
    public static final String API_TEST_POST = ipAddress+"/api/TestPost";
    public static final String API_HISTORY_GET_ALL = ipAddress+"/api/Histories";
}
