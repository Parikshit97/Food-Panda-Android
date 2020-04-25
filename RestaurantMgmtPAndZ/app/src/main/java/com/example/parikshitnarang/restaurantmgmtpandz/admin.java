package com.example.parikshitnarang.restaurantmgmtpandz;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class admin extends AppCompatActivity {
    DatabaseAdmin dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        dba=new DatabaseAdmin(this);
//        dba.insertData("Pari","7503636097","abc@xyz.com");
    }

    public void viewData(View view) {
        TextView tv=(TextView)findViewById(R.id.tv1);
        Cursor res=dba.getAllData();
        if(res.getCount()==0){
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Name:"+ res.getString(0)+"\n");
            buffer.append("Contact No.:"+ res.getString(1)+"\n");
            buffer.append("Email Id:"+ res.getString(2)+"\n\n\n");
        }

        tv.setText(buffer);
    }

    public void custdatabase(View view) {
        Intent intent=new Intent(getApplicationContext(),custdbhandler.class);
        startActivity(intent);
    }

    public void orderdatabse(View view) {
        Intent intent=new Intent(getApplicationContext(),orderdbhandler.class);
        startActivity(intent);
    }

    public void restdatabse(View view) {
        Intent intent=new Intent(getApplicationContext(),restdbhandler.class);
        startActivity(intent);
    }

    public void pricedatabase(View view) {
        Intent intent=new Intent(getApplicationContext(),pricedbhandler.class);
        startActivity(intent);
    }

    public void bookingdb(View view) {
        Intent intent=new Intent(getApplicationContext(),bookingview.class);
        startActivity(intent);
    }
}
