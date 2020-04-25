package com.example.parikshitnarang.restaurantmgmtpandz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PriceDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="price1.db";
    public static final String TABLE_NAME="price_table";
    public static final String COL_1="NAME";
    public static final String COL_2="ITEM";
    public static final String COL_3="PRICE";

    public PriceDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_NAME+ "(NAME TEXT, ITEM TEXT, PRICE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String item, String price) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,item);
        contentValues.put(COL_3,price);
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

    public Integer deleteData(String ITEM) {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ITEM = ?",new String[]{ITEM});
    }

    public boolean updateData(String NAME, String ITEM, String PRICE){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,NAME);
        contentValues.put(COL_2,ITEM);
        contentValues.put(COL_3,PRICE);
        db.update(TABLE_NAME,contentValues,"ITEM = ?",new String[]{ITEM});
        return true;
    }

}
