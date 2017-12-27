package com.example.dell.dathangan.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.dell.dathangan.Model.Cart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 28/11/2017.
 */

public class MySQLite extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";
    // Phiên bản
    private static final int DATABASE_VERSION = 1;

    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "Cart_Manager_2";

    // Tên bảng: Note.
    private static final String TABLE_CART = "Cart";

    private static final String COLUMN_CART_ID ="Cart_Id";
    private static final String COLUMN_CART_ID_MON_AN = "Cart_Id_Mon_An";
    private static final String COLUMN_CART_TEN_MON_AN = "Cart_Ten_Mon_An";
    private static final String COLUMN_CART_MO_TA = "Cart_Mo_Ta";
    private static final String COLUMN_CART_GIA_BAN = "Cart_Gia_Ban";
    private static final String COLUMN_CART_GIA_KHUYEN_MAI = "Cart_Gia_Khuyen_Mai";
    private static final String COLUMN_CART_SRC_IMG = "Cart_Src_Img";
    private static final String COLUMN_CART_NUMBER_ITEM = "Cart_Number_Item";

    // Tạo các bảng.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Script tạo bảng.
        String script = "CREATE TABLE " + TABLE_CART + "("
                + COLUMN_CART_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_CART_ID_MON_AN + " INTEGER,"
                + COLUMN_CART_TEN_MON_AN + " TEXT,"
                + COLUMN_CART_MO_TA + " TEXT,"
                + COLUMN_CART_GIA_BAN + " TEXT,"
                + COLUMN_CART_GIA_KHUYEN_MAI + " TEXT,"
                + COLUMN_CART_SRC_IMG + " TEXT,"
                + COLUMN_CART_NUMBER_ITEM + " INTEGER" + ")";
        // Chạy lệnh tạo bảng.
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);

        // Và tạo lại.
        onCreate(db);
    }

    Context _context;
    public MySQLite(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this._context = context;
    }

    // Thêm Item vào giỏ hàng
    public void addItemToCart(Cart note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CART_ID_MON_AN, note.getID_MonAn());
        values.put(COLUMN_CART_TEN_MON_AN, note.getTenMonAn());
        values.put(COLUMN_CART_MO_TA, note.getMoTa());
        values.put(COLUMN_CART_GIA_BAN, note.getGiaBan());
        values.put(COLUMN_CART_GIA_KHUYEN_MAI, note.getGiaKhuyenMai());
        values.put(COLUMN_CART_SRC_IMG, note.getSrcImg());
        values.put(COLUMN_CART_NUMBER_ITEM, note.getNumberItem());

        // Trèn một dòng dữ liệu vào bảng.
        String msg = "";
        if(db.insert(TABLE_CART, null, values) == -1){
            msg = "Thêm sp vào giỏ hàng thất bại !";
        } else {
            msg = "Thêm sp vào giỏ hàng thành công !";
        }
        Toast.makeText(_context, msg, Toast.LENGTH_SHORT).show();
        // Đóng kết nối database.
        db.close();
    }

    public Cart getDataFromId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from "+ TABLE_CART +" where "+COLUMN_CART_ID_MON_AN+" = "+id+"", null );
        if (cursor.moveToFirst()) {
                Cart note = new Cart();
                note.setID_MonAn(Integer.parseInt(cursor.getString(1)));
                note.setTenMonAn(cursor.getString(2));
                note.setMoTa(cursor.getString(3));
                note.setGiaBan(cursor.getString(4));
                note.setGiaKhuyenMai(cursor.getString(5));
                note.setSrcImg(cursor.getString(6));
                note.setNumberItem(Integer.parseInt(cursor.getString(7)));

                // Thêm vào danh sách.
                return note;
        } else {
            Cart a = new Cart(-1,"","","","","",0);
            return a;
        }
    }

    public Cart getItemFromCart(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CART, new String[] {
                        COLUMN_CART_ID,
                        COLUMN_CART_ID_MON_AN,
                        COLUMN_CART_TEN_MON_AN,
                        COLUMN_CART_MO_TA,
                        COLUMN_CART_GIA_BAN,
                        COLUMN_CART_GIA_KHUYEN_MAI,
                        COLUMN_CART_SRC_IMG,
                        COLUMN_CART_NUMBER_ITEM
        },
                COLUMN_CART_ID_MON_AN + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Cart note = new Cart(
                Integer.parseInt(cursor.getString(1)),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                Integer.parseInt(cursor.getString(2))
        );
        // return note
        return note;
    }

    public ArrayList<Cart> getAllCarts() {
        ArrayList<Cart> noteList = new ArrayList<Cart>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CART;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Cart note = new Cart();
                note.setID_MonAn(Integer.parseInt(cursor.getString(1)));
                note.setTenMonAn(cursor.getString(2));
                note.setMoTa(cursor.getString(3));
                note.setGiaBan(cursor.getString(4));
                note.setGiaKhuyenMai(cursor.getString(5));
                note.setSrcImg(cursor.getString(6));
                note.setNumberItem(Integer.parseInt(cursor.getString(7)));

                // Thêm vào danh sách.
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        // return note list
        return noteList;
    }

    public int updateNote(Cart note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(COLUMN_CART_ID_ITEM, note.getNoteTitle());
        values.put(COLUMN_CART_NUMBER_ITEM, note.getNumberItem());

        // updating row
        return db.update(TABLE_CART, values, COLUMN_CART_ID_MON_AN + " = ?",
                new String[]{String.valueOf(note.getID_MonAn())});
    }

    public void deleteNote(Cart note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, COLUMN_CART_ID_MON_AN + " = ?",
                new String[] { String.valueOf(note.getID_MonAn()) });
        db.close();
        Toast.makeText(_context, "Hủy thành công !", Toast.LENGTH_SHORT).show();
    }

    public void deleteCart() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_CART, null, null);
        db.close();
   }
}
