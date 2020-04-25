package com.example.parikshitnarang.restaurantmgmtpandz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantDatabse extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="restaurant.db";
    public static final String TABLE_NAME="rest_info";
    public static final String COL_1="NAME";
    public static final String COL_2="ADDRESS";
    public static final String COL_3="CONTACT";

    public RestaurantDatabse(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_NAME+ "(NAME TEXT, ADDRESS TEXT, CONTACT TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String address, String contact) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,address);
        contentValues.put(COL_3,contact);
        long result=db.insert(TABLE_NAME,null,contentValues);
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

    public Integer deleteData(String NAME) {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"NAME = ?",new String[]{NAME});
    }

    public boolean updateData(String NAME, String ADDRESS, String CONTACT){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,NAME);
        contentValues.put(COL_2,ADDRESS);
        contentValues.put(COL_3,CONTACT);
        db.update(TABLE_NAME,contentValues,"NAME = ?",new String[]{NAME});
        return true;
    }

}
