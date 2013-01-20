package com.share.PACManager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "jacp_demo.db";
	private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Provider.ServerColumns.TABLE_NAME + " ("
                + Provider.ServerColumns._ID + " INTEGER PRIMARY KEY,"
                + Provider.ServerColumns.SERVER + " TEXT,"
                + Provider.ServerColumns.HTTPS + " INTEGER,"
                + Provider.ServerColumns.PORT + " INTEGER"
                + ");");
        
        db.execSQL("CREATE TABLE " + Provider.LeaderColumns.TABLE_NAME + " ("
        		+ Provider.LeaderColumns._ID + " INTEGER PRIMARY KEY,"
        		+ Provider.LeaderColumns.NAME + " TEXT,"
        		+ Provider.LeaderColumns.TITLE + " TEXT,"
        		+ Provider.LeaderColumns.LEVEL + " INTEGER"
        		+ ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Provider.ServerColumns.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Provider.LeaderColumns.TABLE_NAME);
        onCreate(db);
    }
}