package com.example.parikshitnarang.restaurantmgmtpandz;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class orderdbhandler extends AppCompatActivity {

    HallTableDatabase pdba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdbhandler);
        pdba=new HallTableDatabase(this);
    }

    public void insertClick(View view) {
        EditText name=(EditText)findViewById(R.id.etname14);
        EditText table=(EditText)findViewById(R.id.ettable14);
        EditText hall=(EditText)findViewById(R.id.ethall14);
        String nameet=name.getText().toString();
        String tableet=table.getText().toString();
        String hallet=hall.getText().toString();
        boolean isInserted= pdba.insertData(nameet,tableet,hallet);
        if(isInserted){
            Toast.makeText(this,"Table/Hall info added!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Table/Hall info adding failed!",Toast.LENGTH_SHORT).show();
        }
    }

    public void updateClick(View view) {
        EditText name=(EditText)findViewById(R.id.etname14);
        EditText table=(EditText)findViewById(R.id.ettable14);
        EditText hall=(EditText)findViewById(R.id.ethall14);
        String nameet=name.getText().toString();
        String tableet=table.getText().toString();
        String hallet=hall.getText().toString();
        boolean isUpdated= pdba.updateData(nameet,tableet,hallet);
    }

    public void deleteClick(View view) {
        EditText name=(EditText)findViewById(R.id.etname14);
        Integer res=pdba.deleteData(name.getText().toString());
        if(res!=0){
            Toast.makeText(this,"Table/Hall info deleted!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Table/Hall info not deleted!",Toast.LENGTH_SHORT).show();
        }
    }

    public void viewClick(View view) {
        StringBuffer buffer=new StringBuffer();
        Cursor res=pdba.getAllData();
        while(res.moveToNext()){
            buffer.append("Restaurant: "+res.getString(0)+"\n");
            buffer.append("Table No.: "+res.getString(1)+"\n");
            buffer.append("Hall.: "+res.getString(2)+"\n\n");
        }

        TextView tv1=(TextView)findViewById(R.id.tv14);
        tv1.setText(buffer);
    }
}
