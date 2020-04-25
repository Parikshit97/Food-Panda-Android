package com.example.parikshitnarang.restaurantmgmtpandz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper1 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="SignInInfo1111.db";
    public static final String TABLE_NAME="Login_table";
    public static final String COL_1="NAME";
   // public static final String COL_2="Address";
    public static final String COL_2="PASSWORD";

    public DatabaseHelper1(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+ "(NAME TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String name, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,password);
       long result= db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }

    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}