package com.example.parikshitnarang.restaurantmgmtpandz;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class restdbhandler extends AppCompatActivity {
    RestaurantDatabse rdba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restdbhandler);
        rdba=new RestaurantDatabse(this);
    }

    public void insertClick(View view) {

        EditText name=(EditText)findViewById(R.id.etname);
        EditText address=(EditText)findViewById(R.id.etaddress);
        EditText contact=(EditText)findViewById(R.id.etcontact);
        String nameet=name.getText().toString();
        String addresset=address.getText().toString();
        String contactet=contact.getText().toString();
        boolean isInserted= rdba.insertData(nameet,addresset,contactet);
        if(isInserted){
            Toast.makeText(this,"Restaurant info added!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Restaurant info adding failed!",Toast.LENGTH_SHORT).show();
        }

    }

    public void updateClick(View view) {
        EditText name=(EditText)findViewById(R.id.etname);
        EditText address=(EditText)findViewById(R.id.etaddress);
        EditText contact=(EditText)findViewById(R.id.etcontact);
        String nameet=name.getText().toString();
        String addresset=address.getText().toString();
        String contactet=contact.getText().toString();
        boolean isUpdated= rdba.updateData(nameet,addresset,contactet);
    }

    public void deleteClick(View view) {

        EditText name=(EditText)findViewById(R.id.etname);
        Integer res=rdba.deleteData(name.getText().toString());
        if(res!=0){
            Toast.makeText(this,"Resturant info deleted!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Restaurant info not deleted!",Toast.LENGTH_SHORT).show();
        }

    }

    public void viewClick(View view) {

        StringBuffer buffer=new StringBuffer();
        Cursor res=rdba.getAllData();
        while(res.moveToNext()){
            buffer.append("Restaurant: "+res.getString(0)+"\n");
            buffer.append("Contact: "+res.getString(2)+"\n");
            buffer.append("Address: "+res.getString(1)+"\n\n");
        }

        TextView tv1=(TextView)findViewById(R.id.tv1);
        tv1.setText(buffer);

    }
}
