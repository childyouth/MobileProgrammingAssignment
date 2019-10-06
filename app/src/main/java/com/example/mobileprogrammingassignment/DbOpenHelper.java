package com.example.mobileprogrammingassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper {
    public static SQLiteDatabase mDB;

    private static final String DATABASE_NAME = "MobileProgramming.db";
    private static final int DATABASE_VERSION = 2;
    private DatabaseHelper mDBHelper;
    private Context context;

    private class DatabaseHelper extends SQLiteOpenHelper{

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Database.DB._CREATE0);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + Database.DB._TABLENAME);
            onCreate(db);
        }

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }
    }

    public DbOpenHelper(Context context){
        this.context = context;
    }

    public DbOpenHelper open() throws SQLException{
        mDBHelper = new DatabaseHelper(this.context, DATABASE_NAME,null,DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        mDBHelper.onCreate(mDB);
    }

    public void close(){
        mDB.close();
    }

    public long insertColumn(String userid,String userpw,String username,String userphone, String useraddr){
        ContentValues values = new ContentValues();
        values.put(Database.DB.USERID,userid);
        values.put(Database.DB.USERPW,userpw);
        values.put(Database.DB.USERNAME,username);
        values.put(Database.DB.USERPHONE,userphone);
        values.put(Database.DB.USERADDR,useraddr);
        return mDB.insert(Database.DB._TABLENAME,null,values);
    }

    public Cursor selectColumn(String key){
        return mDB.rawQuery("SELECT " + key + " FROM " + Database.DB._TABLENAME + ";",null);
    }

    public Cursor searchID(String id){
        return mDB.rawQuery("SELECT * FROM " + Database.DB._TABLENAME + " WHERE " + Database.DB.USERID + " = \"" + id + "\";",null);
    }
}
