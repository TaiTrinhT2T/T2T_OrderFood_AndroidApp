package com.example.dell.dathangan.Utils;

import android.content.Context;

/**
 * Created by DELL on 09/11/2017.
 * TODO: Lớp này lưu tất cả các hàm xử lý ảnh
 */

public class BaseImagePreprocessing {
    /**
     * TODO: Tìm ID của Image ứng với tên của ảnh (Trong thư mục mipmap).
     * @param context
     * @param resName tên Image
     * @return ID của Image lưu trong mipmap
     */
    public static int getMipmapResIdByName(Context context,String resName)  {
        String pkgName = context.getPackageName();
        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        // Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
}
