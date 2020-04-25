package com.example.parikshitnarang.restaurantmgmtpandz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdmin extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Admin111.db";
    public static final String TABLE_NAME="Customer_table";
    public static final String COL_1="NAME";
    public static final String COL_2="CONTACT";
    public static final String COL_3="EMAILID";


    public DatabaseAdmin(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_NAME+ "(NAME TEXT, CONTACT TEXT, EMAILID TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String contact, String email) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,contact);
        contentValues.put(COL_3,email);
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

    public boolean updateData(String NAME, String CONTACT, String EMAILID){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,NAME);
        contentValues.put(COL_2,CONTACT);
        contentValues.put(COL_3,EMAILID);
        db.update(TABLE_NAME,contentValues,"NAME = ?",new String[]{NAME});
        return true;
    }

    public Integer deleteData(String NAME){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"NAME = ?",new String[]{NAME});
    }
}
