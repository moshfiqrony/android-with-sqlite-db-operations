package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DBName = "mr.db";
    public static final String TableName= "info";
    public static final String col0= "id";
    public static final String col1= "name";
    public static final String col2= "email";


    @Override
    public void setIdleConnectionTimeout(long idleConnectionTimeoutMs) {
        super.setIdleConnectionTimeout(idleConnectionTimeoutMs);
    }

    public DbHelper(Context context) {
        super(context, DBName, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TableName+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TableName);
        onCreate(db);

    }

    public boolean insertData(String name, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("EMAIL", email);
        long res = db.insert(TableName, null, contentValues);
        if(res == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getData(){
//        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.myapplication/databases/mr.db", null, 0);
        SQLiteDatabase db = SQLiteDatabase.openDatabase("storage/emulated/0/d2_brd/metadata/forms.db", null, 0);
        Log.i("MRDB", db.toString());
        Cursor res = db.rawQuery("select * from "+"forms where jrFormId=?", new String[]{"rzm"});
        return res;
    }
}