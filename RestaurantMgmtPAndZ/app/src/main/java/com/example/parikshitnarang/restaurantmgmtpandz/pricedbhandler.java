package com.example.parikshitnarang.restaurantmgmtpandz;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class pricedbhandler extends AppCompatActivity {

    PriceDatabase pdba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricedbhandler);
        pdba=new PriceDatabase(this);
    }

    public void insertClick(View view) {
        EditText name=(EditText)findViewById(R.id.etname);
        EditText item=(EditText)findViewById(R.id.etitem);
        EditText price=(EditText)findViewById(R.id.etprice);
        String nameet=name.getText().toString();
        String itemet=item.getText().toString();
        String priceet=price.getText().toString();
        boolean isInserted= pdba.insertData(nameet,itemet,priceet);
        if(isInserted){
            Toast.makeText(this,"Item and Price added!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Item and Price adding failed!",Toast.LENGTH_SHORT).show();
        }
    }

    public void updateClick(View view) {
        EditText name=(EditText)findViewById(R.id.etname);
        EditText item=(EditText)findViewById(R.id.etitem);
        EditText price=(EditText)findViewById(R.id.etprice);
        String nameet=name.getText().toString();
        String itemet=item.getText().toString();
        String priceet=price.getText().toString();
        boolean isUpdated= pdba.updateData(nameet,itemet,priceet);
    }

    public void deleteClick(View view) {
        EditText name=(EditText)findViewById(R.id.etname);
        Integer res=pdba.deleteData(name.getText().toString());
        if(res!=0){
            Toast.makeText(this,"Item deleted!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Item not deleted!",Toast.LENGTH_SHORT).show();
        }
    }

    public void viewClick(View view) {
        StringBuffer buffer=new StringBuffer();
        Cursor res=pdba.getAllData();
        while(res.moveToNext()){
            buffer.append("Restaurant: "+res.getString(0)+"\n");
            buffer.append("Item: "+res.getString(1)+"\n");
            buffer.append("Price: "+res.getString(2)+"\n\n");
        }

        TextView tv1=(TextView)findViewById(R.id.tv1);
        tv1.setText(buffer);
    }
}
