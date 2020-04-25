package com.example.parikshitnarang.restaurantmgmtpandz;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class custdbhandler extends AppCompatActivity {
    DatabaseAdmin dba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custdbhandler);
        dba=new DatabaseAdmin(this);
    }

    public void insertClick(View view) {
        EditText name=(EditText)findViewById(R.id.etname);
        EditText email=(EditText)findViewById(R.id.etemail);
        EditText phone=(EditText)findViewById(R.id.etcontact);
            String nameet=name.getText().toString();
            String emailet=email.getText().toString();
            String phoneet=phone.getText().toString();
            boolean isInserted= dba.insertData(nameet,phoneet,emailet);
            if(isInserted){
                Toast.makeText(this,"Customer added!",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Customer adding failed!",Toast.LENGTH_SHORT).show();
            }
    }

    public void updateClick(View view) {
        EditText name=(EditText)findViewById(R.id.etname);
        EditText email=(EditText)findViewById(R.id.etemail);
        EditText phone=(EditText)findViewById(R.id.etcontact);
        String nameet=name.getText().toString();
        String emailet=email.getText().toString();
        String phoneet=phone.getText().toString();
        boolean isUpdated= dba.updateData(nameet,phoneet,emailet);
    }

    public void deleteClick(View view) {
        EditText name=(EditText)findViewById(R.id.etname);
        Integer res=dba.deleteData(name.getText().toString());
        if(res!=0){
            Toast.makeText(this,"Customer deleted!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Customer not deleted!",Toast.LENGTH_SHORT).show();
        }
    }

    public void viewClick(View view) {
        StringBuffer buffer=new StringBuffer();
        Cursor res=dba.getAllData();
        while(res.moveToNext()){
            buffer.append("Name: "+res.getString(0)+"\n");
            buffer.append("Contact: "+res.getString(1)+"\n");
            buffer.append("EmailId: "+res.getString(2)+"\n\n");
        }

        TextView tv1=(TextView)findViewById(R.id.tv1);
        tv1.setText(buffer);
    }
}
