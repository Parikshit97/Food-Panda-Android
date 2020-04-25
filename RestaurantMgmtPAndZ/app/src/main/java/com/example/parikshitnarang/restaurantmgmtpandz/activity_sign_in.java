package com.example.parikshitnarang.restaurantmgmtpandz;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class activity_sign_in extends AppCompatActivity {

    DatabaseHelper1 mydbsignin;
    EditText editPhone, editName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mydbsignin=new DatabaseHelper1(this);
        mydbsignin.insertData("Parikshit Narang","7503636097");
    }

    public void verify(View view) {
        editPhone=(EditText)findViewById(R.id.editPhone);
        editName=(EditText)findViewById(R.id.editName);

        Cursor res=mydbsignin.getAllData();
        if(res.getCount()==0){
            return;
        }

        String[] strings=new String[res.getCount()];
        int flag=0;
        while (res.moveToNext()){
            String name=res.getString(0);
            String pswd=res.getString(1);

            if(name.equals(editName.getText().toString())){
                if(!pswd.equals(editPhone.getText().toString())){
                    Toast.makeText(this,"Invalid Password!",Toast.LENGTH_SHORT).show();
                    flag=1;
                }
            }

            if(pswd.equals(editPhone.getText().toString())) {
                if (!name.equals(editName.getText().toString())) {
                    Toast.makeText(this, "Invalid Username!", Toast.LENGTH_SHORT).show();
                    flag=1;
                }
            }

            if(name.equals(editName.getText().toString())){
                if(pswd.equals(editPhone.getText().toString())){
                    Toast.makeText(this,"Sign In succesfull!",Toast.LENGTH_SHORT).show();
                    flag=1;
                    Intent foruseroptions=new Intent(this,useroptions.class);
                    startActivity(foruseroptions);
                }
            }

        }

        if(flag==0){
            Toast.makeText(this,"Invalid Sign In Credentials!",Toast.LENGTH_SHORT).show();
        }


    }
}
