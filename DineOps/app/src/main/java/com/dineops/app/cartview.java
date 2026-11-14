package com.dineops.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class cartview extends UiActivity {
    public static final String EXTRA_MESSAGE =
            "com.dineops.app.MESSAGE";
    CartDatabase cdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartview);

        cdb=new CartDatabase(this);
    }


    public void deleteClick(View view) {
        EditText etname=(EditText)findViewById(R.id.etname);
        EditText etitem=(EditText)findViewById(R.id.etdelete);

        Integer res=cdb.deleteData(etitem.getText().toString());
        if(res!=0){
            Toast.makeText(this,getString(R.string.message_13),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,getString(R.string.message_14),Toast.LENGTH_SHORT).show();
        }
    }

    public void viewClick(View view) {
        EditText etname=(EditText)findViewById(R.id.etname);
        StringBuffer buffer=new StringBuffer();
        Cursor res=cdb.getAllData();
        while(res.moveToNext()){
            if(res.getString(0).equals(etname.getText().toString())) {
                buffer.append("Name: " + res.getString(0) + "\n");
                buffer.append("Item: " + res.getString(1) + "\n");
                buffer.append("Quantity: " + res.getString(2) + "\n");
                buffer.append("Delivery date: " + res.getString(3) + "\n\n");
            }
        }

        TextView tv1=(TextView)findViewById(R.id.tv1);
        tv1.setText(buffer);
    }

    public void adbview(View view) {
        AlertDialog.Builder alertDialBuilder=new AlertDialog.Builder(this);
        alertDialBuilder.setTitle(getString(R.string.message_15));
        alertDialBuilder.setMessage(getString(R.string.message_16));
        alertDialBuilder.setPositiveButton(getString(R.string.message_10), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText etname=(EditText)findViewById(R.id.etname);
                String message=etname.getText().toString();
                Intent intent=new Intent(getApplicationContext(),bill.class);
                intent.putExtra(EXTRA_MESSAGE, message); startActivity(intent);
            }
        });
        alertDialBuilder.setNegativeButton(getString(R.string.message_17), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog=alertDialBuilder.create();
        alertDialog.show();
    }

    public void updateqty(View view) {

//        EditText etname=(EditText)findViewById(R.id.etname);
//        EditText item=(EditText)findViewById(R.id.etdelete);
//        EditText qty=(EditText)findViewById(R.id.etqty);
//
//        boolean res=cdb.updateData(etid.getText().toString(),etname.getText().toString(),etsurname.getText().toString(),etmarks.getText().toString());
//        if(res==true){
//            Toast.makeText(this,"Data updated!",Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this,"Data not updated!",Toast.LENGTH_SHORT).show();
//        }

    }
}
