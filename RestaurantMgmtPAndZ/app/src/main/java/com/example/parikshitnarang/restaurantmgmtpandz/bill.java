package com.example.parikshitnarang.restaurantmgmtpandz;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class bill extends AppCompatActivity {

    CartDatabase cdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        cdb=new CartDatabase(this);

        Intent intent = getIntent();
        String message = intent.getStringExtra(cartview.EXTRA_MESSAGE);
        TextView tv=(TextView)findViewById(R.id.tvname);
        tv.setText(message);

        TextView tv1=(TextView)findViewById(R.id.tv1);

        Cursor res=cdb.getAllData();
        StringBuffer buffer=new StringBuffer();
        String dd="";
        while (res.moveToNext()){
            if(res.getString(0).equals(message)) {
                if(buffer.indexOf(res.getString(0))==-1) {
                    buffer.append("Name: " + res.getString(0) + "\n");

                }
                buffer.append("Item: " + res.getString(1) + "\n");
                buffer.append("Quantity: " + res.getString(2) + "\n\n");


                if(buffer.indexOf(res.getColumnName(3))==-1){
                    dd+="Delivery Date & time: " + res.getString(3) + "\n\n";
                }
            }

        }

        buffer.append(dd);
        tv1.setText(buffer);
        }

    public void adbview(View view) {
    }
}
