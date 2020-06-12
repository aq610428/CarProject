package com.car.notver.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "north.db";
    private static final int version = 1;
    public static final String DB_KEEP_NEW = "north_keep_new";//阅读记录

    public DBHelper(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(DbManager.getCreateTable(InfoBean.class, DB_KEEP_NEW));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
