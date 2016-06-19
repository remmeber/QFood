package com.example.rhg.outsourcing.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rhg.outsourcing.bean.ShoppingCartBean;
import com.example.rhg.outsourcing.constants.AppConstants;

/**
 * desc:购物车数据库辅助类
 * author：remember
 * time：2016/5/28 16:41
 * email：1013773046@qq.com
 */
public class AccountDBHelper extends SQLiteOpenHelper {
    /**
     * 数据库名称常量
     */
    public static final String DATABASE_NAME = "q_account.db3";
    /**
     * 数据库版本常量
     */
    private static final int DATABASE_VERSION = 3;
    /**
     * 购物车表
     */
    public static final String Q_SHOPPING_CART_TABLE = "q_shopping_cart_table";
    /*地址表*/
    public static final String Q_ADDRESS_TABLE = "q_address_table";
    /*搜索历史记录表*/
    public static final String Q_SEARCH_HISTORY_TABLE = "q_search_history_table";
    /*收藏表*/
//    public static final String Q_LIKE = "Q_like";

    private static AccountDBHelper helper;

    private static Context APPLICATION_CONTEXT;

    public static void init(Context context) {
        APPLICATION_CONTEXT = context;
    }

    public AccountDBHelper() {
        super(APPLICATION_CONTEXT, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static AccountDBHelper getInstance() {
        if (helper == null) {
            helper = new AccountDBHelper();

        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TB_ADDRESS = "create table " + Q_ADDRESS_TABLE + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AppConstants.ADDRESS_CREATE_TIME + " text,"
                + AppConstants.NAME_FOR_ADDRESS + " text,"
                + AppConstants.PHONE_FOR_ADDRESS + " text,"
                + AppConstants.ADDRESS_CONTENT + " text"
                + ");";
        db.execSQL(CREATE_TB_ADDRESS);
        String CREATE_TB_SHOPPING_CART = "create table " + Q_SHOPPING_CART_TABLE + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ShoppingCartBean.KEY_PRODUCT_ID + " text,"
                + ShoppingCartBean.KEY_NUM + " text"
                + ");";
        db.execSQL(CREATE_TB_SHOPPING_CART);

        String CREATE_TB_SEARCH_HISTORY = "create table " + Q_SEARCH_HISTORY_TABLE + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "searched text,"
                + "num INTEGER"
                + ");";
        db.execSQL(CREATE_TB_SEARCH_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }
}
