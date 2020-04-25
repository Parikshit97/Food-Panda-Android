package com.example.parikshitnarang.restaurantmgmtpandz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CartDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="cart1.db";
    public static final String TABLE_NAME="cart_table";
    public static final String COL_1="NAME";
    public static final String COL_2="ITEM";
    public static final String COL_3="QTY";
    public static final String COL_4="DDATE";
    public static final String COL_5="TOTAL";


    public CartDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_NAME+ "(NAME TEXT, ITEM TEXT, QTY TEXT, DDATE TEXT, TOTAL TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String item, String qty,String ddate, String total) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,item);
        contentValues.put(COL_3,qty);
        contentValues.put(COL_4,ddate);
        contentValues.put(COL_5,total);
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

    public boolean updateData(String name, String item, String qty,String ddate, String total){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,item);
        contentValues.put(COL_3,qty);
        contentValues.put(COL_4,ddate);
        contentValues.put(COL_5,total);
        db.update(TABLE_NAME,contentValues,"ITEM = ?",new String[]{item});
        return true;
    }

}
