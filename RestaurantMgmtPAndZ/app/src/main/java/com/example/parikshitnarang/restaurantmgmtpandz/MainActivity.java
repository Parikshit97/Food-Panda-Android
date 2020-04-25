package com.example.parikshitnarang.restaurantmgmtpandz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn, btnSignUp, btnAdmin;
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn=(Button)findViewById(R.id.btnSignActive);
        btnSignUp=(Button)findViewById(R.id.btnSignUp);
        btnAdmin=(Button)findViewById(R.id.btnAdmin);

        txtSlogan=(TextView)findViewById(R.id.txtSlogan);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signIn=new Intent(MainActivity.this,activity_sign_in.class);
                startActivity(signIn);

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp=new Intent(MainActivity.this,activity_sign_up.class);
                startActivity(signUp);
            }
        });

//        btnAdmin.setOnClickListener(new View.OnClickListener() {
//                Intent admin=new Intent(MainActivity.this,admin.class);
//                startActivity(admin);
//
//
//        });

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent admin=new Intent(MainActivity.this,admin.class);
                startActivity(admin);
            }
        });

    }



}
