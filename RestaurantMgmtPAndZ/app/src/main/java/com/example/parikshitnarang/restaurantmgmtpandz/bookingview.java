package com.example.parikshitnarang.restaurantmgmtpandz;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class bookingview extends AppCompatActivity {
    BookingsDatabase bdba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingview);
        bdba=new BookingsDatabase(this);
    }

    public void insertClick(View view) {
        EditText name=(EditText)findViewById(R.id.etname15);
        EditText table=(EditText)findViewById(R.id.ettable15);
        EditText hall=(EditText)findViewById(R.id.ethall15);
        EditText dateet=(EditText)findViewById(R.id.etdate15);
        String nameet=name.getText().toString();
        String tableet=table.getText().toString();
        String hallet=hall.getText().toString();
        boolean isInserted= bdba.insertData(nameet,tableet,hallet,dateet.getText().toString());
        if(isInserted){
            Toast.makeText(this,"Data added!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Data adding failed!",Toast.LENGTH_SHORT).show();
        }
    }

    public void updateClick(View view) {

        EditText name=(EditText)findViewById(R.id.etname15);
        EditText table=(EditText)findViewById(R.id.ettable15);
        EditText hall=(EditText)findViewById(R.id.ethall15);
        EditText dateet=(EditText)findViewById(R.id.etdate15);
        String nameet=name.getText().toString();
        String tableet=table.getText().toString();
        String hallet=hall.getText().toString();
        boolean isUpdated= bdba.updateData(nameet,tableet,hallet,dateet.getText().toString());
    }

    public void deleteClick(View view) {
        EditText name=(EditText)findViewById(R.id.etname15);
        Integer res=bdba.deleteData(name.getText().toString());
        if(res!=0){
            Toast.makeText(this,"Booking deleted!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Booking not deleted!",Toast.LENGTH_SHORT).show();
        }
    }

    public void viewClick(View view) {
        StringBuffer buffer=new StringBuffer();
        Cursor res=bdba.getAllData();
        while(res.moveToNext()){
            buffer.append("Name: "+res.getString(0)+"\n");
            buffer.append("Table: "+res.getString(1)+"\n");
            buffer.append("Hall: "+res.getString(2)+"\n");
            buffer.append("Date: "+res.getString(3)+"\n\n");
        }

        TextView tv1=(TextView)findViewById(R.id.tv15);
        tv1.setText(buffer);
    }
}
