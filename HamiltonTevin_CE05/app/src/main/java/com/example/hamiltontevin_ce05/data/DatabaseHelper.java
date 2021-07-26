// Tevin Hamilton
// JAV2 - 2003
// DatabaseHelper
package com.example.hamiltontevin_ce05.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_FILE = "articles.db";
    private static final int DATABASE_VERSION = 1;


    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
            DataContract.DATA_TABLE + " (" +
            DataContract.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DataContract.TITLE + " TEXT, " +
            DataContract.THUMBNAIL + " TEXT, " +
            DataContract.BODY + " TEXT)";

    private  static SQLiteDatabase mDB;
    static private DatabaseHelper mInstance = null;

    public static DatabaseHelper getInstance(Context context) {
        if(mInstance==null) {
            mInstance = new DatabaseHelper(context);
        }
        return mInstance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_FILE, null, DATABASE_VERSION);
        mDB = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor query(String[] projection, String selection,
                        String[] selectionArgs, String sortOrder){
        return mDB.query(DataContract.DATA_TABLE,
                projection,selection,selectionArgs,null,null,sortOrder);
    }

    public void insertPerson(String _title, String _thumbnail, String _body){
        ContentValues cv = new ContentValues();
        cv.put(DataContract.TITLE, _title);
        cv.put(DataContract.THUMBNAIL, _thumbnail);
        cv.put(DataContract.BODY, _body);
        mDB.insert(DataContract.DATA_TABLE, null, cv);
    }

    public int getCount () {

        Cursor c = mDB.query(DataContract.DATA_TABLE, null, null, null, null, null,null);
        return c.getCount();
    }
}
